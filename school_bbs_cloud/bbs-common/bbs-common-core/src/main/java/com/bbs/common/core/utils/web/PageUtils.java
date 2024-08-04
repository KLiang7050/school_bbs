package com.bbs.common.core.utils.web;

import com.bbs.common.core.domain.PageDomain;
import com.github.pagehelper.PageHelper;

public class PageUtils extends PageHelper {

    public static void startPageNotOrder(){
        PageDomain pageDomain = PageSupport.getPageDomain();
        int pageNum = pageDomain.getPageNum();
        int pageSize = pageDomain.getPageSize();
        PageHelper.startPage(pageNum,pageSize,true,false,false);
    }

    public static void startPageNotOrderAndCount(){
        PageDomain pageDomain = PageSupport.getPageDomain();
        int pageNum = pageDomain.getPageNum();
        int pageSize = pageDomain.getPageSize();
        PageHelper.startPage(pageNum,pageSize,false,false,false);
    }
}
