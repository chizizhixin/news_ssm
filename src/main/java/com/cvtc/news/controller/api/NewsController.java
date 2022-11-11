//package com.cdvtc.news.controller.api;
//
//import com.cdvtc.news.dao.NewsDao;
//import com.cdvtc.news.dao.impl.NewsDaoImpl;
//import com.cdvtc.news.model.News;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller("newsApiController")
//@RequestMapping("/api/news")
//public class NewsController {
//
//	/**
//	 * 获取新闻
//	 */
//    @GetMapping("/{id}")
//	public @ResponseBody News getNews(@PathVariable int id){
//		NewsDao newsDao = new NewsDaoImpl();
//		return newsDao.getNewsById(id);
//	}
//
//	/**
//	 * 新增新闻
//	 * @param news
//	 * @return
//	 */
//	@PostMapping("")
//	public @ResponseBody Integer addNews(@RequestBody News news){
//		NewsDao newsDao = new NewsDaoImpl();
//		return  newsDao.addNews(news);
//	}
//
//	/**
//	 * 更新新闻
//	 * @param news
//	 */
//	@PutMapping("")
//	public void updateNews(@RequestBody News news){
//		NewsDao newsDao = new NewsDaoImpl();
//		newsDao.updateNews(news);
//	}
//
//	/**
//	 * 删除新闻
//	 * @param id
//	 * @return
//	 */
//
//    @DeleteMapping("/{id}")
//	public boolean deleteNews(@PathVariable int id){
//		NewsDao newsDao = new NewsDaoImpl();
//		return true;
//	}
//
//}
