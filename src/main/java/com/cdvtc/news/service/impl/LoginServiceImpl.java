package com.cdvtc.news.service.impl;

import com.cdvtc.news.dao.UserDao;
import com.cdvtc.news.model.User;
import com.cdvtc.news.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDao userDao;

	@Override
	public User login(String account, String password) {
		return userDao.login(account,password);
	}
}
