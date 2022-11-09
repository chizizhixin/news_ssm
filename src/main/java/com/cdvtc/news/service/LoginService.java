package com.cdvtc.news.service;

import com.cdvtc.news.model.User;

public interface LoginService {
	User login(String account, String password);
}
