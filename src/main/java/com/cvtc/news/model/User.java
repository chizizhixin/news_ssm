package com.cvtc.news.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户
 */
public class User {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 账户
     */
    private String account;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String photo;

    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthday;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 注册时间
     */
    private Date regDate;

    /**
     * 是否禁用
     */
    private Boolean forbidden;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Boolean getForbidden() {
        return forbidden;
    }

    public void setForbidden(Boolean forbidden) {
        this.forbidden = forbidden;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", regDate=" + regDate +
                ", forbidden=" + forbidden +
                '}';
    }
}
