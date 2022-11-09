package com.cdvtc.news.controller;


import com.cdvtc.news.dao.CategoryDao;
import com.cdvtc.news.dao.NewsDao;
import com.cdvtc.news.dao.TagDao;
import com.cdvtc.news.dao.impl.CategoryDaoImpl;
import com.cdvtc.news.dao.impl.NewsDaoImpl;
import com.cdvtc.news.dao.impl.TagDaoImpl;
import com.cdvtc.news.model.Category;
import com.cdvtc.news.model.News;
import com.cdvtc.news.model.Tag;
import com.cdvtc.news.service.IndexService;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HttpServletBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class indexController {

	@Autowired
	private IndexService indexService;
	@RequestMapping("/")
	public String home(Model model, Integer category , Integer tag, HttpSession session){
		//获取分类和标签参数
		List<Category> categories  = indexService.getAllCategories();
		List<Tag> tagList = indexService.getAllTags();
		model.addAttribute("tagList",tagList);
		session.setAttribute("categories",categories);
		List<News> newsList;
		List<News> hotNews = indexService.getHotNews();
		session.setAttribute("hotNews", hotNews);
		if(category != null) {
			newsList = indexService.getNewsByCategory(category);
		}
		else if(tag != null) {
			newsList = indexService.getNewsByTag(tag);
		}
		else {
			newsList = indexService.getStickNews(10); //默认查询置顶（首页）新闻
		}
        model.addAttribute("newsList",newsList);
		return "index";
	}

}
