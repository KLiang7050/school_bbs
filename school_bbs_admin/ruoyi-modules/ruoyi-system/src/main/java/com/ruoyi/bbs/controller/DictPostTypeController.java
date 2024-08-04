package com.ruoyi.bbs.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.bbs.domain.DictPostType;
import com.ruoyi.bbs.service.IDictPostTypeService;
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
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 贴子类型字典Controller
 * 
 * @author ckl
 * @date 2024-03-26
 */
@RestController
@RequestMapping("/app/type")
public class DictPostTypeController extends BaseController
{
    @Autowired
    private IDictPostTypeService dictPostTypeService;

    /**
     * 查询贴子类型字典列表
     */
    @RequiresPermissions("bbs:type:list")
    @GetMapping("/list")
    public TableDataInfo list(DictPostType dictPostType)
    {
        startPage();
        List<DictPostType> list = dictPostTypeService.selectDictPostTypeList(dictPostType);
        return getDataTable(list);
    }

    /**
     * 导出贴子类型字典列表
     */
    @RequiresPermissions("bbs:type:export")
    @Log(title = "贴子类型字典", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DictPostType dictPostType)
    {
        List<DictPostType> list = dictPostTypeService.selectDictPostTypeList(dictPostType);
        ExcelUtil<DictPostType> util = new ExcelUtil<DictPostType>(DictPostType.class);
        util.exportExcel(response, list, "贴子类型字典数据");
    }

    /**
     * 获取贴子类型字典详细信息
     */
    @RequiresPermissions("bbs:type:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(dictPostTypeService.selectDictPostTypeById(id));
    }

    /**
     * 新增贴子类型字典
     */
    @RequiresPermissions("bbs:type:add")
    @Log(title = "贴子类型字典", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DictPostType dictPostType)
    {
        return toAjax(dictPostTypeService.insertDictPostType(dictPostType));
    }

    /**
     * 修改贴子类型字典
     */
    @RequiresPermissions("bbs:type:edit")
    @Log(title = "贴子类型字典", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DictPostType dictPostType)
    {
        return toAjax(dictPostTypeService.updateDictPostType(dictPostType));
    }

    /**
     * 删除贴子类型字典
     */
    @RequiresPermissions("bbs:type:remove")
    @Log(title = "贴子类型字典", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dictPostTypeService.deleteDictPostTypeByIds(ids));
    }
}
