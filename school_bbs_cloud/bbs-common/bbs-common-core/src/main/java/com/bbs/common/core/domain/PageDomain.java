package com.bbs.common.core.domain;

import org.junit.platform.commons.util.StringUtils;

public class PageDomain {
    /**
     * 当前记录起始索引
     */
    private int pageNum;

    /**
     * 每页显示记录数
     */
    private int pageSize;

    /**
     * 排序列
     */
    private String orderByColumn;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

}
