package com.cdvtc.news.dao;

import com.cdvtc.news.model.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NewsDao {
    /**
     * 获取分类新闻列表
     * @param categoryId 分类编号
     * @return
     */
    List<News> getNewsByCategory(@Param("categoryId") Integer categoryId);

    /**
     * 获取标签新闻列表
     * @param tagId 标签编号
     * @return
     */
    List<News> getNewsByTag(@Param("tagId") Integer tagId);

    /**
     * 获取置顶新闻（首页展示）
     * @param limit 限制数量
     * @return
     */
    List<News> getStickNews(@Param("limit") int limit);


    /**
     * 获取所有新闻（不分页）
     * @return
     */
    List<News> getAllNews();

    /**
     * 增加新闻
     * @param news
     */
    Integer addNews(News news);

    /**
     * 更新新闻
     * @param news
     */
    void updateNews(News news);

    /**
     * 根据ID获取新闻
     * @param newsId
     * @return
     */
    News getNewsById(int newsId);

    /**
     * 获取热点新闻（24小时内发布或评论，前10条）
     * @return
     */
    List<News> getHotNews();

    /**
     * 获取新闻的推荐新闻（根据新闻标签）
     * @param newsId
     * @return
     */
    List<News> getRecommendedNews(int newsId);

    /**
     * 更新新闻点击计数（+1）
     * @param newsId
     */
    void updateClickCount(int newsId);


    /**
     * 按照分类统计新闻数
     * @return
     */
    List<Map<String, Object>> statNewsCountByCategory();

    /**
     * 按照标签统计新闻籹
     * @return
     */
    List<Map<String, Object>> statNewsCountByTag();


    /**
     * 按照日期统计新闻评论数
     * @return
     */
    List<Map<String, Object>> statNewsCommentCountByDate();


    /**
     * 按照用户统计新闻评论数
     * @return
     */
    List<Map<String, Object>> statNewsCommentCountByUser();

    /**
     * 分页查询新闻列表
     * @param pageNumer 当前分页
     * @param pageSize 分页大小
     * @return
     */
    Page<News> getPagedNews(int pageNumer, int pageSize);
}
