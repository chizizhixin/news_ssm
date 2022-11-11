package com.cvtc.news.controller;

import com.cvtc.news.dao.UserDao;
import com.cvtc.news.dao.impl.UserDaoImpl;
import com.cvtc.news.model.User;
import com.cvtc.news.service.SignupService;
import com.cvtc.news.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class signupController {

	@Autowired
	private SignupService signupService;

	@RequestMapping("/signup")
	public String signup(){
		return "signup";
	}

	@RequestMapping("/signup_deal")
	public String deal(User user, Model model){

		/**
		 * 数据验证
		 */
		UserDao userDao = new UserDaoImpl();
		String error_account = null;
		if(signupService.isUserExist("account", user.getAccount())){
			error_account = "账户已经存在！";
			model.addAttribute("error_account", error_account);
		}
		String error_nickname = null;
		if(signupService.isUserExist("nickname", user.getNickname())){
			error_nickname = "昵称已经存在！";
			model.addAttribute("error_nickname", error_nickname);
		}
		String error_mobile = null;
		if(signupService.isUserExist("mobile", user.getMobile())){
			error_mobile = "手机号码已经存在！";
			model.addAttribute("error_mobile", error_mobile);
		}
		String error_email = null;
		if(user.getEmail() != null && signupService.isUserExist("email", user.getEmail())){ // 注意：email为null时
			error_email = "电子邮件已经存在！";
			model.addAttribute("error_email", error_email);
		}
		/**
		 * 根据验证结果进行页面跳转
		 */
		if(error_account==null && error_nickname==null && error_mobile==null && error_email==null){ //验证通过
			user.setPassword(Md5Util.md5(user.getPassword())); //密码使用MD5加密保存
			signupService.addUser(user);

			//进入登陆页面
//			request.setAttribute("page", "login.jsp");
//			request.setAttribute("message", "账户注册成功，请使用该账户登陆系统。");
//			request.getRequestDispatcher("alert_jump.jsp").forward(request, response);
			return "redirect:/";
		} else { //返回至注册页面
			return "forward:signup";
//			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}

}
