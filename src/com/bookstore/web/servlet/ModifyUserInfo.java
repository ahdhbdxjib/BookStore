package com.bookstore.web.servlet;

import com.bookstore.exception.UserException;
import com.bookstore.model.User;
import com.bookstore.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
@WebServlet("/modifyUserInfo")
public class ModifyUserInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            BeanUtils.populate(user,req.getParameterMap());
            System.out.println(user);
            UserService us= new UserService();
            us.modifyUserInfo(user);
            resp.sendRedirect(req.getContextPath()+"/modifyUserInfoSuccess.jsp");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }
}
