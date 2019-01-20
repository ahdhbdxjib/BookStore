package com.bookstore;

import com.bookstore.exception.UserException;
import com.bookstore.model.User;
import com.bookstore.service.UserService;
import com.bookstore.utils.C3P0Utils;
import com.bookstore.utils.SendJMail;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Test {
/*    void happy(Integer a, Cosumer<Integer> com){
        com.accept();
    }*/

    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            nums.add(i * i);
        }





/*        for (int i = 1; i < 21; i++) {
          //  SendJMail.sendMail("1245569850@qq.com","王锴林，我发来第"+i+"生日祝贺");

        }*/

       /* UserService us = new UserService();
        User user = new User();
        user.setPassword("dadfa");
        user.setIntroduce("hello");
        user.setUsername("name");
        user.setTelephone("123412435");
        System.out.println(C3P0Utils.getConnection());*/
/*        try {
            us.register(user);
        } catch (UserException e) {
            e.printStackTrace();
        }*/
/*        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=  DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false","root","admin");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user(username,PASSWORD,gender,email,telephone,introduce,activeCode,state,role,registTime)" +
                    "values (111,111,11111,1111,11,1111,111,1111,null,null)");
            preparedStatement.executeUpdate();
            System.out.println(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}
