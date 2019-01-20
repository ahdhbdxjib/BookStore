package com.bookstore.web.servlet;

import com.bookstore.model.PageResult;
import com.bookstore.model.Product;
import com.bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showProductByPage")
public class ShowProductByPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String currentpage = request.getParameter("page");
        ProductService productService = new ProductService();
        int page = 1;
        if(currentpage!=null&&!"".equals(currentpage)){
            page = Integer.parseInt(currentpage);
        }
        //返回 的数据使用JavaBean封装
        PageResult<Product> pageResult= productService.findBooks(category,page);
        request.setAttribute("pageResult",pageResult);

        request.getRequestDispatcher("/product_list.jsp").forward(request,response);

    }
}

