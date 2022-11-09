package com.cdvtc.news.service;

import com.cdvtc.news.model.Category;
import com.cdvtc.news.model.News;
import com.cdvtc.news.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IndexService {

	List<Category> getAllCategories();

	List<Tag> getAllTags();

	List<News> getHotNews();

	List<News> getNewsByCategory(Integer category);

	List<News> getNewsByTag(Integer tag);

	List<News> getStickNews(int i);
}
