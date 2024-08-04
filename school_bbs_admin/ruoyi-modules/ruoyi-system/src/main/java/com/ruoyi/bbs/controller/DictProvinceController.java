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
import com.ruoyi.bbs.domain.DictProvince;
import com.ruoyi.bbs.service.IDictProvinceService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 省份字典表Controller
 * 
 * @author ckl
 * @date 2024-04-05
 */
@RestController
@RequestMapping("/app/province")
public class DictProvinceController extends BaseController
{
    @Autowired
    private IDictProvinceService dictProvinceService;

    /**
     * 查询省份字典表列表
     */
    @RequiresRoles("yw")
    @GetMapping("/list")
    public TableDataInfo list(DictProvince dictProvince)
    {
        startPage();
        List<DictProvince> list = dictProvinceService.selectDictProvinceList(dictProvince);
        return getDataTable(list);
    }

    /**
     * 导出省份字典表列表
     */
    @RequiresRoles("yw")
    @Log(title = "省份字典表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DictProvince dictProvince)
    {
        List<DictProvince> list = dictProvinceService.selectDictProvinceList(dictProvince);
        ExcelUtil<DictProvince> util = new ExcelUtil<DictProvince>(DictProvince.class);
        util.exportExcel(response, list, "省份字典表数据");
    }

    /**
     * 获取省份字典表详细信息
     */
    @RequiresRoles("yw")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(dictProvinceService.selectDictProvinceById(id));
    }

    /**
     * 新增省份字典表
     */
    @RequiresRoles("yw")
    @Log(title = "省份字典表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DictProvince dictProvince)
    {
        return toAjax(dictProvinceService.insertDictProvince(dictProvince));
    }

    /**
     * 修改省份字典表
     */
    @RequiresRoles("yw")
    @Log(title = "省份字典表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DictProvince dictProvince)
    {
        return toAjax(dictProvinceService.updateDictProvince(dictProvince));
    }

    /**
     * 删除省份字典表
     */
    @RequiresRoles("yw")
    @Log(title = "省份字典表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dictProvinceService.deleteDictProvinceByIds(ids));
    }
}
