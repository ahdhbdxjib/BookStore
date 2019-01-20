package com.bookstore.web.servlet;

import com.bookstore.exception.UserException;
import com.bookstore.model.User;
import com.bookstore.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import sun.misc.UUDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String checkcode_client = request.getParameter("checkcode");
        String checkcode_sever = (String)request.getSession().getAttribute("checkcode_session");
        System.out.println(checkcode_client);
        System.out.println(checkcode_sever);
        if(!checkcode_client.equals(checkcode_sever)){
            request.setAttribute("check_code","验证码错误请重新输入");
            request.getRequestDispatcher("/register.jsp").forward(request,response);

            return;
        }
        User user = new User();
        try {
            //1。使用bean将数据转化位模型
            BeanUtils.populate(user, request.getParameterMap());
            //将数据位null的设置初值；
            user.setActiveCode(UUID.randomUUID().toString());
            user.setRole("普通用户");
            user.setRegistTime(new Date());

            System.out.println(user);
            UserService rs = new UserService();
            rs.register(user);
            //注册成功
            request.getRequestDispatcher("/registersuccess.jsp").forward(request,response);

        } catch (UserException ue) {
            ue.printStackTrace();
            //注册失败返回一段内容用来提示
            request.setAttribute("register_err",ue.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("转模型失败");
        }

    }
}
