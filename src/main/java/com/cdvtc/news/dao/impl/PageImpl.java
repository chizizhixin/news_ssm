package com.cdvtc.news.dao.impl;

import com.cdvtc.news.dao.Page;

import java.util.List;

/**
 * 查询分页接口实现
 *
 */
public class PageImpl<T> implements Page<T> {
    /**
     * 当前分页
     */
    private Integer pageNumber = 1;

    /**
     * 分页大小
     */
    private Integer pageSize = 10;

    /**
     * 总记录行数
     */
    private Integer totalRows;

    /**
     * 分页数据
     */
    private List<T> pagedData;

    @Override
    public int getPageNumber() {
        return this.pageNumber;
    }

    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    @Override
    public int getTotalRows() {
        return this.totalRows;
    }

    @Override
    public List<T> getPagedData() {
        return this.pagedData;
    }

    @Override
    public int getTotalPages() {
        int totalPages = totalRows/pageSize;
        if(totalRows % pageSize > 0){ //不能整除时，总页数加1
            totalPages++;
        }
        return totalPages;
    }

    @Override
    public int getStartRow() {
        return pageSize * (pageNumber - 1) + 1;
    }

    @Override
    public int getEndRow() {
        int endRow = 0;
        if(totalRows >= pageSize * pageNumber) {
            endRow = pageSize * pageNumber;
        } else if(totalRows > pageSize * (pageNumber - 1)) {
            endRow = totalRows;
        } else {
            endRow = getStartRow();
        }
        return endRow;
    }

    @Override
    public int getPreviousPage() {
        return pageNumber>1 ? pageNumber-1 : pageNumber;
    }

    @Override
    public int getNextPage() {
        return pageNumber<getTotalPages() ? pageNumber+1: pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public void setPagedData(List<T> pagedData) {
        this.pagedData = pagedData;
    }

    @Override
    public String toString() {
        return "PageImpl{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", totalRows=" + totalRows +
                ", pagedData=" + pagedData +
                '}';
    }
}
