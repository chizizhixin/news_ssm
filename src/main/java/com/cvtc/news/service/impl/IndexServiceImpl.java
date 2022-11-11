package com.cvtc.news.service.impl;

import com.cvtc.news.dao.CategoryDao;
import com.cvtc.news.dao.NewsDao;
import com.cvtc.news.dao.TagDao;
import com.cvtc.news.model.Category;
import com.cvtc.news.model.News;
import com.cvtc.news.model.Tag;
import com.cvtc.news.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IndexServiceImpl implements IndexService {
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private TagDao tagDao;
	@Autowired
	private NewsDao newsDao;
	@Override
	public List<Tag> getAllTags() {
		return tagDao.getAllTags();
	}

	@Override
	public List<News> getHotNews() {
		return newsDao.getHotNews();
	}

	@Override
	public List<News> getNewsByCategory(Integer category) {
		return newsDao.getNewsByCategory(category);
	}

	@Override
	public List<News> getNewsByTag(Integer tag) {
		return newsDao.getNewsByTag(tag);
	}

	@Override
	public List<News> getStickNews(int i) {
		return newsDao.getStickNews(i);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}
}
