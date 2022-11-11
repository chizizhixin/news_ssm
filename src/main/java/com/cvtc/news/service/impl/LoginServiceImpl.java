package com.cvtc.news.service.impl;

import com.cvtc.news.dao.UserDao;
import com.cvtc.news.model.User;
import com.cvtc.news.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDao userDao;

	@Override
	public User login(String account, String password) {
		return userDao.login(account,password);
	}
}
