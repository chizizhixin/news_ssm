package com.cvtc.news.service.impl;

import com.cvtc.news.dao.UserDao;
import com.cvtc.news.model.User;
import com.cvtc.news.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SignupServiceImpl implements SignupService {


	@Autowired
	private UserDao userDao;

	@Override
	public boolean isUserExist(String key, String value) {
		return userDao.isUserExisted(key, value);
	}

	@Override
	public void addUser(User user) {
         userDao.addUser(user);
	}
}
