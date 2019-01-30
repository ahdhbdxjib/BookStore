package com.bookstore.web.servlet;

import com.bookstore.model.Product;
import com.bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 将书籍存在购物车之中
 */
@WebServlet("/addCart")
public class AddCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        ProductService productService = new ProductService();
        Product product= productService.findBook(id);
        //从session域中获取对象
        Map<Product,Integer> cart = (Map<Product, Integer>) req.getSession().getAttribute("cart");
        //判断session域中是否有cart对象，如果没有就创建一个
        if(cart==null){
            //如果session域中没有的话就创建一个对象将其放在
            cart = new HashMap<Product,Integer>();
            cart.put(product,1);
            req.getSession().setAttribute("cart",cart);
        }else{
            //如果购物车有数据的时候就将数据的个数加一，如果没有商品就将商品直接放进
            if(cart.containsKey(product)){
                //将个数加一
                cart.put(product,cart.get(product)+1);
            }else{
                //否则直接放入
                cart.put(product,1);
            }
            req.getSession().setAttribute("cart",cart);
        }
        //用户选择继续购物还是就算购物车
        String a1= "<a href=\""+req.getContextPath()+"/showProductByPage?category=计算机"+"\">继续购物</a>";
        String a2= "&nbsp&nbsp&nbsp<a href=\""+req.getContextPath()+"/cart.jsp\">进入购物车</a>";
        resp.getWriter().write(a1);
        resp.getWriter().write(a2);
        //req.getRequestDispatcher("/cart.jsp").forward(req,resp);
        //遍历cart
       /* for (Map.Entry<Product,Integer> productIntegerEntry : cart.entrySet()){
            System.out.println(productIntegerEntry);
        }*/
    }

}
