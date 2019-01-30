package com.bookstore.dao;

import com.bookstore.model.Order;
import com.bookstore.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public static void addOrder(Order order) throws SQLException {
        String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
        List<Object> papms = new ArrayList<>();
        papms.add(order.getId());
        papms.add(order.getMoney());
        papms.add(order.getReceiverAddress());
        papms.add(order.getReceiverName());
        papms.add(order.getReceiverPhone());
        papms.add(order.getPaystate());
        papms.add(order.getOrdertime());
        papms.add(order.getUser().getId());
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        qr.update(sql,papms.toArray());
    }
}
