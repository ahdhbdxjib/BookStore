package com.bookstore.service;

import com.bookstore.dao.UserDao;
import com.bookstore.exception.UserException;
import com.bookstore.model.User;
import com.bookstore.utils.SendJMail;

import java.sql.SQLException;

public class UserService {
    UserDao userDao = new UserDao();

    public void register(User user) throws UserException {

        try {
            userDao.addUser(user);
            //用户在登录成功之后向用户发送验证吗；
            String link = "http://localhost:8080/BookStore/active?activeCode=" + user.getActiveCode();
            String msg = "<a href=\"" + link + "\">欢迎注册网上书城，点击激活</a>";
            System.out.println(msg);
            SendJMail.sendMail(user.getEmail(), msg);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("用户注册失败");
        }

    }

    public void activeUser(String activeCode) throws UserException {
        User user1 = null;
        try {
            user1 = UserDao.findUserByActiveCode(activeCode);
            UserDao.updateState(activeCode);
        } catch (Exception e) {
            throw new UserException("激活失败");
        }
        /**
         * 在抛出异常的时候，不能将异常在try中抛出，否则catch中会自己抛出异常
         */
        if (user1 == null) {
            throw new UserException("用户名或密码错误");
        }
        if (user1 != null && user1.getState() == 1) {
            throw new UserException("用户已经激活");
        }
    }

    public User findUsrById(String id) throws UserException {
        User user = null;
        try {
            user = UserDao.findUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("未知错误");
        }
        if (user == null) {
            throw new UserException("用户不存在");

        }
        return user;
    }

    public User logIn(String username, String password) throws UserException {
        User user = null;
        try {
            user = UserDao.findUser(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("服务器错误");
        }
        if (user == null) {
            throw new UserException("用户不存在");

        }
        if (user.getState() == 0) {
            throw new UserException("用户未激活请登录邮箱激活");
        }
        return user;
    }

    public void modifyUserInfo(User user) throws UserException {
        try {
            UserDao.updataInfo(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("未知错误");
        }
    }

}
