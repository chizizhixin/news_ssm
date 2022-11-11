package com.cvtc.news.util;

import java.sql.*;

/**
 * 数据库连接工具类
 */
public class DBConnection {
    /**
     * 获取数据库连接
     */
    public static Connection getConnection() throws SQLException {

        // 注册驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 获取连接,URL
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/news-sys?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Shanghai&&"
                + "user=root&password=123456");

        return con;
    }

    public static void main(String[] args) throws SQLException {
        Connection con = DBConnection.getConnection();
        //获取数据
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from category");// 执行SQL查询
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println("编号：" + id + ", 类别：" + name);
        }

        //释放连接资源
        rs.close();
        st.close();
        con.close();
    }

}
