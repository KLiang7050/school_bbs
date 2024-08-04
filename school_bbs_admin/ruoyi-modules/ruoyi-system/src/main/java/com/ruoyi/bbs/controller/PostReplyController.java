package com.ruoyi.bbs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.utils.DateUtils;
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
import com.ruoyi.bbs.domain.PostReply;
import com.ruoyi.bbs.service.IPostReplyService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 贴子回复Controller
 * 
 * @author ckl
 * @date 2024-04-05
 */
@RestController
@RequestMapping("/app/reply")
public class PostReplyController extends BaseController
{
    @Autowired
    private IPostReplyService postReplyService;

    /**
     * 查询贴子回复列表
     */
    @RequiresPermissions("bbs:reply:list")
    @GetMapping("/list")
    public TableDataInfo list(PostReply postReply)
    {
        startPage();
        List<PostReply> list = postReplyService.selectPostReplyList(postReply);
        return getDataTable(list);
    }

    /**
     * 导出贴子回复列表
     */
    @RequiresPermissions("bbs:reply:export")
    @Log(title = "贴子回复", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PostReply postReply)
    {
        List<PostReply> list = postReplyService.selectPostReplyList(postReply);
        ExcelUtil<PostReply> util = new ExcelUtil<PostReply>(PostReply.class);
        util.exportExcel(response, list, "贴子回复数据");
    }

    /**
     * 获取贴子回复详细信息
     */
    @RequiresPermissions("bbs:reply:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(postReplyService.selectPostReplyById(id));
    }

    /**
     * 新增贴子回复
     */
    @RequiresPermissions("bbs:reply:add")
    @Log(title = "贴子回复", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PostReply postReply)
    {
        postReply.setCreateTime(DateUtils.getNowDate());
        postReply.setUpdateTime(DateUtils.getNowDate());
        return toAjax(postReplyService.insertPostReply(postReply));
    }

    /**
     * 修改贴子回复
     */
    @RequiresPermissions("bbs:reply:edit")
    @Log(title = "贴子回复", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PostReply postReply)
    {
        postReply.setUpdateTime(DateUtils.getNowDate());
        return toAjax(postReplyService.updatePostReply(postReply));
    }

    /**
     * 删除贴子回复
     */
    @RequiresPermissions("bbs:reply:remove")
    @Log(title = "贴子回复", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(postReplyService.deletePostReplyByIds(ids));
    }
}
