package com.bookstore.service;

import com.bookstore.dao.ProductDao;
import com.bookstore.model.PageResult;
import com.bookstore.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    ProductDao productDao = new ProductDao();

    public PageResult<Product> findBooks(String category, int page) {


        try {
            PageResult<Product> pr = new PageResult<>();
            long totalConut = productDao.count(category);
            //总的数目
            pr.setTotalCount(totalConut);
            //总的页数
            int totalPage = (int) Math.ceil(totalConut * 1.0 / pr.getPageSize());
            pr.setTotalPage(totalPage);
            //设置当前页数
            pr.setCurrentPage(page);
            List<Product> list = productDao.findBooks(category, page, pr.getPageSize());
            pr.setList(list);
            return pr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

}
