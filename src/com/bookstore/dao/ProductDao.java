package com.bookstore.dao;

import com.bookstore.model.Product;
import com.bookstore.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    //抛出异常使得service可以捕获
    public Long count(String category) throws SQLException {
        long count = 0;
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select count(*) from products where 1=1";
        //如果用户传过来的参数是空的话就默认返回所有的书籍，否则就是所选的书籍类别
        if (category != null && !"".equals(category)) {
            sql += " and category = ?";
            count = (Long) qr.query(sql, new ScalarHandler(), category);
        } else {
            count = (Long) qr.query(sql, new ScalarHandler());
        }
        return count;
    }

    /**
     * @param category 用户出传来的分类
     * @param page     用户需求的页码
     * @param pageSize 用户需求的页的大小
     * @return 返回一个集合给用户
     * @throws SQLException
     */
    public List<Product> findBooks(String category, int page, int pageSize) throws SQLException {
        String sql = "select * from products where 1=1";
        List<Object> params = new ArrayList<>();
        if (category != null && !"".equals(category)) {
            sql += " and category = ?";
            params.add(category);
        }
        sql += " limit ?,?";
        int start = (page - 1) * pageSize;
        int length = pageSize;
        params.add(start);
        params.add(length);
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        return qr.query(sql, new BeanListHandler<Product>(Product.class), params.toArray());
    }

/*    @Test
    public void test() throws SQLException {
        ProductDao dao = new ProductDao();
        String category = "计算机";
        long count = dao.count(category);

        System.out.println(count);
        System.out.println("--------------------");
         List<Product> books = dao.findBooks(category,1,5);
        for (Product b: books){
            System.out.println(b);
        }
    }*/
}
