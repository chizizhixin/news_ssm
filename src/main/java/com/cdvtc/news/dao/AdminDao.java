package com.cdvtc.news.dao;

import com.cdvtc.news.model.Admin;

import java.util.List;

public interface AdminDao {
    /**
     * 根据编号获取管理员（编辑）
     * @param adminId
     * @return
     */
    Admin getAdminById(int adminId);


    /**
     * 管理员（编辑）登陆
     * @param account (账号/手机号/电子邮件)
     * @param password
     * @return
     */
    Admin login(String account, String password);

    /**
     * 获取所有管理员
     * @return
     */
    List<Admin> getAllAdmins();
}
