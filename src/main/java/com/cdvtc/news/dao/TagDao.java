package com.cdvtc.news.dao;

import com.cdvtc.news.model.Tag;

import java.util.List;
import java.util.Set;

public interface TagDao {
    /**
     * 获取所有标签
     * @return
     */
    List<Tag> getAllTags();

    /**
     * 根据新闻编号获取标签集合
     * @param newsId
     * @return
     */
    Set<Tag> getTagsByNewsId(int newsId);

    /**
     * 为新闻添加标签
     * @param newsId
     * @param tagIds
     */
    void addTagsForNews(int newsId, Set<Integer> tagIds);

    /**
     * 更新新闻标签
     * @param newsId
     * @param tagIds
     */
    void updateTagsForNews(int newsId, Set<Integer> tagIds);
}
