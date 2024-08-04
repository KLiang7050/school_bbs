package com.bbs.post.controller;

import com.bbs.common.core.domain.R;
import com.bbs.common.core.domain.post.entity.DictPostType;
import com.bbs.post.service.IDictPostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/type/")
public class DictPostTypeController {
    @Autowired
    private IDictPostTypeService dictPostTypeService;

    @GetMapping("list")
    public R list() {
        List<DictPostType> list = dictPostTypeService.selectAll();
        return R.ok(list);
    }
}
