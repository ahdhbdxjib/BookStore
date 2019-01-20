package com.bookstore.dao;

import com.bookstore.model.User;
import com.bookstore.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public void addUser(User user) throws SQLException {
        //1.使用c3p0连接池访问数据库
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        //2.数据库查询字符串
        String sql = "insert into user" +
                "(username,PASSWORD,gender,email,telephone,introduce,activeCode,state,role,registTime)" +
                "values(?,?,?,?,?,?,?,?,?,?)";
        //3.将参数传入
        List<Object> list = new ArrayList<>();
        list.add(user.getUsername());
        list.add(user.getPassword());
        list.add(user.getGender());
        list.add(user.getEmail());
        list.add(user.getTelephone());
        list.add(user.getIntroduce());
        list.add(user.getActiveCode());
        list.add(user.getState());
        list.add(user.getRole());
        list.add(user.getRegistTime());
        //3.执行查询
        qr.update(sql, list.toArray());
    }
    //通过验证码激活用户
    public static User findUserByActiveCode(String activeCode) throws SQLException {
        //1.使用c3p0连接池访问数据库
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        //2.数据库查询字符串
        String sql = "select * from user where activeCode=?";
        return qr.query(sql,new BeanHandler<User>(User.class),activeCode);
    }
    //更新用户的状态
    public static void updateState(String activeCode) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        //2.数据库查询字符串
        String sql = "update user set state = 1 where activeCode=?";
        qr.update(sql,activeCode);
    }
    //通过用户名和密码找到用户
    public static User findUser(String name,String password) throws SQLException {
        //1.使用c3p0连接池访问数据库
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        //2.数据库查询字符串
        String sql = "select * from user where username=?&&PASSWORD=?";
        return qr.query(sql,new BeanHandler<User>(User.class),name,password);
    }

    public static User findUserById(String id) throws SQLException {
        //1.使用c3p0连接池访问数据库
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        //2.数据库查询字符串
        String sql = "select * from user where id=?";
        return qr.query(sql,new BeanHandler<User>(User.class),id);
    }

    /**
     * 更改用户信息
     * @param user
     * @throws SQLException
     */
    public static void updataInfo( User user) throws SQLException{
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        //2.数据库查询字符串
        String sql = "update user set password = ? ,gender = ?,telephone = ? where id = ?";
         qr.update(sql,user.getPassword(),user.getGender(),user.getTelephone(),user.getId());

    }









}
