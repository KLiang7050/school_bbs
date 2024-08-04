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
import com.ruoyi.bbs.domain.LikeUser;
import com.ruoyi.bbs.service.ILikeUserService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 点赞用户关联Controller
 * 
 * @author ckl
 * @date 2024-04-05
 */
@RestController
@RequestMapping("/app/like")
public class LikeUserController extends BaseController
{
    @Autowired
    private ILikeUserService likeUserService;

    /**
     * 查询点赞用户关联列表
     */
    @RequiresPermissions("bbs:like:list")
    @GetMapping("/list")
    public TableDataInfo list(LikeUser likeUser)
    {
        startPage();
        List<LikeUser> list = likeUserService.selectLikeUserList(likeUser);
        return getDataTable(list);
    }

    /**
     * 导出点赞用户关联列表
     */
    @RequiresPermissions("bbs:like:export")
    @Log(title = "点赞用户关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LikeUser likeUser)
    {
        List<LikeUser> list = likeUserService.selectLikeUserList(likeUser);
        ExcelUtil<LikeUser> util = new ExcelUtil<LikeUser>(LikeUser.class);
        util.exportExcel(response, list, "点赞用户关联数据");
    }

    /**
     * 获取点赞用户关联详细信息
     */
    @RequiresPermissions("bbs:like:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(likeUserService.selectLikeUserById(id));
    }

    /**
     * 新增点赞用户关联
     */
    @RequiresPermissions("bbs:like:add")
    @Log(title = "点赞用户关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LikeUser likeUser)
    {
        return toAjax(likeUserService.insertLikeUser(likeUser));
    }

    /**
     * 修改点赞用户关联
     */
    @RequiresPermissions("bbs:like:edit")
    @Log(title = "点赞用户关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LikeUser likeUser)
    {
        return toAjax(likeUserService.updateLikeUser(likeUser));
    }

    /**
     * 删除点赞用户关联
     */
    @RequiresPermissions("bbs:like:remove")
    @Log(title = "点赞用户关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(likeUserService.deleteLikeUserByIds(ids));
    }
}
