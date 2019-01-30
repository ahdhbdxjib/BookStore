package com.bookstore.service;

import com.bookstore.dao.OrderDao;
import com.bookstore.dao.OrderItemDao;
import com.bookstore.model.Order;
import com.bookstore.model.OrderItem;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    /*public void createOrder(Order order, OrderItem items){


    }*/

    private OrderDao orderDao;
    private OrderItemDao orderItemDao;
    /**
     * 将数据插入到dao
     * @param order
     */
    public void createOrder(Order order){
        try {
            orderDao.addOrder(order);

            orderItemDao.addItem(order.getItems());
            //减库存

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
