package com.cvtc.news.dao.impl;

import com.cvtc.news.dao.UserDao;
import com.cvtc.news.model.User;

import com.cvtc.news.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User login(String account, String password) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from user where (account=? or mobile=? or email=?) and password=? and forbidden=?");
            pst.setString(1, account);
            pst.setString(2, account);
            pst.setString(3, account);
            pst.setString(4, password);
            pst.setBoolean(5, false);
            ResultSet rs = pst.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User();
                user.setAccount(account);
                user.setId(rs.getInt("id"));
                user.setBirthday(rs.getDate("birthday"));
                user.setMobile(rs.getString("mobile"));
                user.setEmail(rs.getString("email"));
                user.setPhoto(rs.getString("photo"));
                user.setRegDate(rs.getTimestamp("regdate"));
                user.setNickname(rs.getString("nickname"));
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addUser(User user) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("insert into user(account, password, nickname, photo, birthday, email, mobile, regDate, forbidden) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, user.getAccount());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getNickname());
            pst.setString(4, user.getPhoto());
            pst.setDate(5, new Date(user.getBirthday().getTime())); //需要将java.util.Date转化为java.sql.Date
            pst.setString(6, user.getEmail());
            pst.setString(7, user.getMobile());
            pst.setTimestamp(8, new Timestamp(new java.util.Date().getTime())); //当前时间作为注册时间
            pst.setBoolean(9, false); //默认为不禁用

            int result = pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();

            return result > 0; //大于0表示成功创建一行
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean isUserExisted(String key, String value) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from user where " + key + "=?");
            pst.setString(1, value);

            ResultSet rs = pst.executeQuery();

            boolean result = rs.next(); //判断是否查询到数据

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from user");

            //封装结果为列表数据
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setAccount(rs.getString("account"));
                user.setPhoto(rs.getString("photo"));
                user.setForbidden(rs.getBoolean("forbidden"));
                user.setNickname(rs.getString("nickname"));
                user.setBirthday(rs.getTimestamp("birthday"));
                user.setMobile(rs.getString("mobile"));
                user.setEmail(rs.getString("email"));
                user.setRegDate(rs.getTimestamp("regdate"));

                users.add(user);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void forbiddenUser(int userId, boolean forbidden) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("update user set forbidden=? where id=?");
            pst.setBoolean(1, forbidden);
            pst.setInt(2, userId);

            pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePassword(String account, String password) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("update user set password=? where account=?");
            pst.setString(1, password);
            pst.setString(2, account);

            pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            //获取连接
            Connection con = DBConnection.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("update user set nickName=?, photo=?, birthday=?, email=?, mobile=? where account=?");
            pst.setString(1, user.getNickname());
            pst.setString(2, user.getPhoto());
            pst.setDate(3, new Date(user.getBirthday().getTime()));
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getMobile());
            pst.setString(6, user.getAccount());

            pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.login("test", "test"));
        System.out.println(userDao.isUserExisted("account", "test"));
    }
}
