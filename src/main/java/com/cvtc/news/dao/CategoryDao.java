package com.cvtc.news.dao;

import com.cvtc.news.model.Category;

import java.util.List;

public interface CategoryDao {
    /**
     * 获取所有新闻分类
     * @return
     */
    List<Category> getAllCategories();

}
