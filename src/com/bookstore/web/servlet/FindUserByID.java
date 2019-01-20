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

@WebServlet("/findUserById")
public class FindUserByID extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        //el表达式取数据的顺序： page request session application
        UserService us = new UserService();
        try {
            User user = us.findUsrById(userId);
            request.setAttribute("u",user);
            request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request,response);
        } catch (UserException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }

    }
}
