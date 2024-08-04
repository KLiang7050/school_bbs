package com.bbs.common.core.utils.web;

import com.bbs.common.core.domain.PageDomain;
import com.bbs.common.core.utils.web.ServletUtils;

/**
 * 表格数据处理
 * 
 * @author ruoyi
 */
public class PageSupport
{
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(Integer.parseInt(ServletUtils.getParameter(PAGE_NUM)));
        pageDomain.setPageSize(Integer.parseInt(ServletUtils.getParameter(PAGE_SIZE)));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }
}
