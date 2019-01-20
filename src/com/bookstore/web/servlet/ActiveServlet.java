package com.bookstore.web.servlet;

import com.bookstore.exception.UserException;
import com.bookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/active")
public class ActiveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response中含有中文字符，需要将中文字符转化位UTF
        response.setHeader("content-type","text/html;charset=utf-8");
/*        response.setContentType("UTF-8");
        response.setCharacterEncoding("UTF-8");*/
        String activeCode = request.getParameter("activeCode");
        UserService us = new UserService();
        try {
            us.activeUser(activeCode);
            response.getWriter().write("激活成功！！");
        } catch (UserException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }
    }
}
