package com.cvtc.news.dao.impl;

import com.cvtc.news.dao.CategoryDao;
import com.cvtc.news.model.Category;
import com.cvtc.news.util.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> getAllCategories() {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from category");

            //封装结果为列表数据
            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();

                int id = rs.getInt("id");
                String name = rs.getString("name");
                category.setId(id);
                category.setName(name);

                categories.add(category);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 利用main方式进行调用，实现简单的单元测试
     * @param args
     */
    public static void main(String[] args) {
        CategoryDao categoryDao = new CategoryDaoImpl();
        System.out.println(categoryDao.getAllCategories());
    }
}
