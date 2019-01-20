package com.bookstore.web.servlet;

import com.bookstore.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myaccount")
public class MyAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }else{
            response.sendRedirect(request.getContextPath()+"/myAccount.jsp");
        }
    }
}
