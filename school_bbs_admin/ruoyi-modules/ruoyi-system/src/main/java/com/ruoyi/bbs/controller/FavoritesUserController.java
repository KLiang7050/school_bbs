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
import com.ruoyi.bbs.domain.FavoritesUser;
import com.ruoyi.bbs.service.IFavoritesUserService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 收藏用户关联Controller
 * 
 * @author ckl
 * @date 2024-04-05
 */
@RestController
@RequestMapping("/app/favorites")
public class FavoritesUserController extends BaseController
{
    @Autowired
    private IFavoritesUserService favoritesUserService;

    /**
     * 查询收藏用户关联列表
     */
    @RequiresPermissions("bbs:favorites:list")
    @GetMapping("/list")
    public TableDataInfo list(FavoritesUser favoritesUser)
    {
        startPage();
        List<FavoritesUser> list = favoritesUserService.selectFavoritesUserList(favoritesUser);
        return getDataTable(list);
    }

    /**
     * 导出收藏用户关联列表
     */
    @RequiresPermissions("bbs:favorites:export")
    @Log(title = "收藏用户关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FavoritesUser favoritesUser)
    {
        List<FavoritesUser> list = favoritesUserService.selectFavoritesUserList(favoritesUser);
        ExcelUtil<FavoritesUser> util = new ExcelUtil<FavoritesUser>(FavoritesUser.class);
        util.exportExcel(response, list, "收藏用户关联数据");
    }

    /**
     * 获取收藏用户关联详细信息
     */
    @RequiresPermissions("bbs:favorites:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(favoritesUserService.selectFavoritesUserById(id));
    }

    /**
     * 新增收藏用户关联
     */
    @RequiresPermissions("bbs:favorites:add")
    @Log(title = "收藏用户关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FavoritesUser favoritesUser)
    {
        return toAjax(favoritesUserService.insertFavoritesUser(favoritesUser));
    }

    /**
     * 修改收藏用户关联
     */
    @RequiresPermissions("bbs:favorites:edit")
    @Log(title = "收藏用户关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FavoritesUser favoritesUser)
    {
        return toAjax(favoritesUserService.updateFavoritesUser(favoritesUser));
    }

    /**
     * 删除收藏用户关联
     */
    @RequiresPermissions("bbs:favorites:remove")
    @Log(title = "收藏用户关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(favoritesUserService.deleteFavoritesUserByIds(ids));
    }
}
