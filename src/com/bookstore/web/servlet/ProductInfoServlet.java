package com.bookstore.web.servlet;

import com.bookstore.model.Product;
import com.bookstore.service.ProductService;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/productInfo")
public class ProductInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        ProductService productService = new ProductService();
        Product p = productService.findBook(id);
        req.setAttribute("product",p);
        req.getRequestDispatcher("/product_info.jsp").forward(req,resp);

    }
}
