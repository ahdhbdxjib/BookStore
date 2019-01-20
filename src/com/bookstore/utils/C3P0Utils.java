package com.bookstore.utils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public class C3P0Utils {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
    //获得数据源
    public static DataSource getDataSource() {
        return dataSource;
    }
    //获得连接对象
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //释放资源
    public static void release(Connection con, PreparedStatement par, ResultSet re) {
        if (re != null) {
            try {
                re.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (par != null) {
            try {
                par.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
