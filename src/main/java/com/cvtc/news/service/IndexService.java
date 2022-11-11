package com.cvtc.news.service;

import com.cvtc.news.model.Category;
import com.cvtc.news.model.News;
import com.cvtc.news.model.Tag;

import java.util.List;

public interface IndexService {

	List<Category> getAllCategories();

	List<Tag> getAllTags();

	List<News> getHotNews();

	List<News> getNewsByCategory(Integer category);

	List<News> getNewsByTag(Integer tag);

	List<News> getStickNews(int i);
}
