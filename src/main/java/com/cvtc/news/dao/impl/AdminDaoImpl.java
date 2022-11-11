package com.cvtc.news.dao.impl;

import com.cvtc.news.dao.AdminDao;
import com.cvtc.news.model.Admin;
import com.cvtc.news.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin getAdminById(int adminId) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from admin where id = ?");
            pst.setInt(1, adminId); //填充参数，注意参数编号从1开始
            ResultSet rs = pst.executeQuery();
            Admin admin = new Admin();
            if (rs.next()) {
                admin.setId(rs.getInt("id"));
                admin.setAccount(rs.getString("account"));
                admin.setName(rs.getString("name"));

                //密码不写入（1、并没有使用 2、防止敏感信息泄露）
                //admin.setPassword(rs.getString("password"));
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Admin login(String account, String password) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from admin where account=? and password=?");
            pst.setString(1, account); //填充参数，注意参数编号从1开始
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            Admin admin = new Admin();
            if (rs.next()) {
                admin.setId(rs.getInt("id"));
                admin.setAccount(rs.getString("account"));
                admin.setName(rs.getString("name"));

                //密码不写入（1、并没有使用 2、防止敏感信息泄露）
                //admin.setPassword(rs.getString("password"));

                //释放连接资源
                rs.close();
                pst.close();
                con.close();

                //返回结果
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Admin> getAllAdmins() {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from admin");

            //封装结果为列表数据
            List<Admin> admins = new ArrayList<>();
            while (rs.next()) {
                Admin admin = new Admin();

                admin.setId(rs.getInt("id"));
                admin.setAccount(rs.getString("account"));
                admin.setName(rs.getString("name"));

                admins.add(admin);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return admins;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        AdminDao adminDao = new AdminDaoImpl();
        System.out.println(adminDao.getAdminById(1));
        System.out.println(adminDao.login("admin", "123456"));
    }
}
