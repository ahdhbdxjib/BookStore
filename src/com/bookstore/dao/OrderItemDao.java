package com.bookstore.dao;

import com.bookstore.model.OrderItem;
import com.bookstore.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDao {
    public static void addItem(List<OrderItem> items ) throws SQLException {
        String sql = "insert into orderitem (order_id,product_id,buynum) values (?,?,?)";
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        //      取出
        for (OrderItem item : items){
            qr.update(sql,item.getOrder().getId(),item.getP().getId(),item.getBuynum());

        }

    }
}
