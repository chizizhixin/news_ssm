package com.cdvtc.news.model;

/**
 * (新闻)分类
 */
public class Category {
    /**
     * 编号
     */
    private int id;

    /**
     * 名称
     */
    private String name;

    private  Integer count;
//    private News news;/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
//    public News getNews() {
//        return news;
//    }
//
//    public void setNews(News news) {
//        this.news = news;
//    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
//                ", news=" + news +
                '}';
    }
}
