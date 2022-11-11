package com.cvtc.news.service;

import com.cvtc.news.model.User;

public interface LoginService {
	User login(String account, String password);
}
