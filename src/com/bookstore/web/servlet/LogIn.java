package com.bookstore.web.servlet;

import com.bookstore.exception.UserException;
import com.bookstore.model.User;
import com.bookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LogIn extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserService us = new UserService();
        try {
            User user = us.logIn(username, password);
            req.getSession().setAttribute("user", user);
            if ("管理员".equals(user.getRole())) {
                resp.sendRedirect(req.getContextPath() + "/admin/login/home.jsp");
            } else {

                //使用转发会有表单重复提交的现象
                //req.getRequestDispatcher("index.jsp").forward(req,resp);
                //使用重定向表单不会重复提交。
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            }
        } catch (UserException e) {
            e.printStackTrace();
            req.setAttribute("login_msg", e.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
