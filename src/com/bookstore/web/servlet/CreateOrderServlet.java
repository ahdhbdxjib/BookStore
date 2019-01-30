package com.bookstore.web.servlet;

import com.bookstore.model.Order;
import com.bookstore.model.OrderItem;
import com.bookstore.model.Product;
import com.bookstore.model.User;
import com.bookstore.service.OrderService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/createOrder")
public class CreateOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.getWriter().write("非法访问>>>>>");
            return;
        }
        Order order = new Order();
        try {
            List<OrderItem> items = new ArrayList();
            BeanUtils.populate(order, req.getParameterMap());
            System.out.println(order);
            order.setId(UUID.randomUUID().toString());
            order.setOrdertime(new Date());
            order.setUser(user);
            Map<Product,Integer> cart = (Map<Product, Integer>) req.getSession().getAttribute("cart");
            if(cart == null || cart.size() == 0){
                resp.getWriter().write("购物车为空");
                return;
            }
            Double total = 0.0;
            for(Map.Entry<Product,Integer> entry : cart.entrySet()){
                OrderItem orderItem = new OrderItem();
                orderItem.setBuynum(entry.getValue());
                orderItem.setP(entry.getKey());
                total += entry.getKey().getPrice() * entry.getValue();
                orderItem.setOrder(order);
                //将获得的添加到数组之中
                items.add(orderItem);
            }
            order.setMoney(total);
            order.setItems(items);
            //调用业务层
            OrderService orderService = new OrderService();
            orderService.createOrder(order);
            //然后再移除session
            req.getSession().removeAttribute("cart");

         /*   System.out.println("___________________________");
            System.out.println("订单："+order);
            System.out.println("订单详情");
            for (OrderItem item : items){
                System.out.println("详情："+ item.getP()+"数量："+item.getBuynum()+"总价："+item.getOrder().getMoney());
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
