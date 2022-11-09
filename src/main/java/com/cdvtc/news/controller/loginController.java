package com.cdvtc.news.controller;

import com.cdvtc.news.dao.CategoryDao;
import com.cdvtc.news.dao.UserDao;
import com.cdvtc.news.dao.impl.CategoryDaoImpl;
import com.cdvtc.news.dao.impl.UserDaoImpl;
import com.cdvtc.news.model.Category;
import com.cdvtc.news.model.User;
import com.cdvtc.news.service.LoginService;
import com.cdvtc.news.util.Md5Util;
import com.cdvtc.news.util.MybatisUtil;
import com.cdvtc.news.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
public class loginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping("/login")
	public  String login(Model model){
		return "login";
	}
	@RequestMapping("/login_deal")
	public  String deal(String account, String password, Model model, HttpServletRequest request, HttpSession session){
//		 UserDao userDao = new UserDaoImpl();
//		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
//		UserDao userDao =  sqlSession.getMapper(UserDao.class);
//		UserDao userDao = MybatisUtil.getMapper(UserDao.class);
//		 UserDao userDao =  MybatisUtil.getMapper(UserDao.class);
//		 User user = userDao.login(account, Md5Util.md5(password)); // 注意：密码需要先进行MD5加密
		User user = loginService.login(account, Md5Util.md5(password)); // 注意：密码需要先进行MD5加密
		if(user != null){ //登陆成功
			session.setAttribute("user", user);
			session.setAttribute("ipAddress", request.getRemoteAddr()); // 保存登陆地址
//        response.sendRedirect("index.jsp");  //重定向至首页

//			request.setAttribute("page", "index.jsp");
//			request.setAttribute("pageName", "首页");
//			request.getRequestDispatcher("delay_jump.jsp").forward(request, response);
		return  "redirect:/";//重定向首页
		} else { //登陆失败
			model.addAttribute("error","登陆失败!");
//			request.getRequestDispatcher("login.jsp").forward(request, response); //前转(同一个request对象)至登陆页面
			return "forward:login";
		}

	}

	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate(); // 销毁Session对象
		return  "redirect:/";//重定向首页
	}

}
