package com.cvtc.news.dao;

import com.cvtc.news.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * 用户登陆
     * @param account (账号/手机号/电子邮件)
     * @param password
     * @return
     */
    User login(@Param("account") String account, @Param("password")  String password);

    /**
     * 增加用户
     * @param user
     * @return boolean 是否创建成功
     */
    boolean addUser(User user);

    /**
     * 判断用户是否已经存在
     * @param key （只能是：account:账户 nickname:昵称  mobile:手机号码 email:电子邮件）
     * @param value
     * @return
     */
    boolean isUserExisted(@Param("key") String key,@Param("value") String value);

    /**
     * 获取所有用户
     * @return
     */
    List<User> getAllUsers();

    /**
     * 禁用用户
     * @param userId
     * @param forbidden true:禁用， false:取消禁用
     */
    void forbiddenUser(int userId, boolean forbidden);

    /**
     * 修改用户密码
     * @param account
     * @param password
     */
    void updatePassword(String account, String password);

    /**
     * 更新用户信息（不包括密码）
     * @param user
     */
    void updateUser(User user);
}
