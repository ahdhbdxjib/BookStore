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

@WebServlet("/logout")
public class LogOut extends HttpServlet {
    @Override
    /**
     * 登出的servlet
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将session设置为无效
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/index.jsp");

    }

}
