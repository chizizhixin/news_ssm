package com.cdvtc.news.service.impl;

import com.cdvtc.news.dao.CategoryDao;
import com.cdvtc.news.dao.NewsDao;
import com.cdvtc.news.dao.TagDao;
import com.cdvtc.news.model.Category;
import com.cdvtc.news.model.News;
import com.cdvtc.news.model.Tag;
import com.cdvtc.news.service.IndexService;
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
