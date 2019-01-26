package com.bookstore.web.servlet;

import com.bookstore.model.Product;
import com.bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/changeNum")
public class ChangeNumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String num = req.getParameter("num");
        ProductService productService = new ProductService();
        Product p = productService.findBook(id);
        Map<Product,Integer> cart = (Map<Product, Integer>) req.getSession().getAttribute("cart");
        //如果数据库中有数据且cart中有
        if(cart.containsKey(p)){
            if("0".equals(num)){
                cart.remove(p);
            }else
            {
                cart.put(p,Integer.parseInt(num));
            }
        }
        //再将cart放置到session中
        req.getSession().setAttribute("cart",cart);
        resp.sendRedirect(req.getContextPath()+"/cart.jsp");
    }
}
