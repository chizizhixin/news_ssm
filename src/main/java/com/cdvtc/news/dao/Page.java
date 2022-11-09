package com.cdvtc.news.dao;

import java.util.List;

/**
 * 查询分页接口
 * 使用泛型可适用于所有实体模型
 */
public interface Page<T> {
    /**
     * 获取当前分页号
     * @return 当前分页
     */
    int getPageNumber();

    /**
     * 获取分页大小
     * @return 分页大小
     */
    int getPageSize();

    /**
     * 获取总记录行数
     * @return 总行数
     */
    int getTotalRows();

    /**
     * 获取当前页面数据
     * @return 分页数据
     */
    List<T> getPagedData();

    /**
     * 获取总页数
     * @return 总页数
     */
    int getTotalPages();

    /**
     * 获取开始行
     * @return 开始行
     */
    int getStartRow();

    /**
     * 获取结束行
     * @return 结束行
     */
    int getEndRow();

    /**
     * 获取前一页
     * @return 前一页
     */
    int getPreviousPage();

    /**
     * 获取下一页
     * @return 下一页
     */
    int getNextPage();
}
