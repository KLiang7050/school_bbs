package com.ruoyi.bbs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.security.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.bbs.domain.PostContent;
import com.ruoyi.bbs.service.IPostContentService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 贴子内容Controller
 * 
 * @author ckl
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/app/content")
public class PostContentController extends BaseController
{
    @Autowired
    private IPostContentService postContentService;

    /**
     * 查询贴子内容列表
     */
    @RequiresPermissions("bbs:content:list")
    @GetMapping("/list")
    public TableDataInfo list(PostContent postContent)
    {
        startPage();
        List<PostContent> list = postContentService.selectPostContentList(postContent);
        return getDataTable(list);
    }

    /**
     * 导出贴子内容列表
     */
    @RequiresPermissions("bbs:content:export")
    @Log(title = "贴子内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PostContent postContent)
    {
        List<PostContent> list = postContentService.selectPostContentList(postContent);
        ExcelUtil<PostContent> util = new ExcelUtil<PostContent>(PostContent.class);
        util.exportExcel(response, list, "贴子内容数据");
    }

    /**
     * 获取贴子内容详细信息
     */
    @RequiresPermissions("bbs:content:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(postContentService.selectPostContentById(id));
    }

    /**
     * 新增贴子内容
     */
    @RequiresPermissions("bbs:content:add")
    @Log(title = "贴子内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PostContent postContent)
    {
        return toAjax(postContentService.insertPostContent(postContent));
    }

    /**
     * 修改贴子内容
     */
    @RequiresPermissions("bbs:content:edit")
    @Log(title = "贴子内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PostContent postContent)
    {
        return toAjax(postContentService.updatePostContent(postContent));
    }

    /**
     * 删除贴子内容
     */
    @RequiresPermissions("bbs:content:remove")
    @Log(title = "贴子内容", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(postContentService.deletePostContentByIds(ids));
    }
}
