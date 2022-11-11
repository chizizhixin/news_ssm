package com.cvtc.news.service;

import com.cvtc.news.model.User;

public interface SignupService {


	boolean isUserExist(String key, String value);

	void addUser(User user);
}
