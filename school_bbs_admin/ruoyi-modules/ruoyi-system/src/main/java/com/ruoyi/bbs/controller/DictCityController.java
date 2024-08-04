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
import com.ruoyi.bbs.domain.DictCity;
import com.ruoyi.bbs.service.IDictCityService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 城市字典表Controller
 * 
 * @author ckl
 * @date 2024-04-05
 */
@RestController
@RequestMapping("/app/city")
public class DictCityController extends BaseController
{
    @Autowired
    private IDictCityService dictCityService;

    /**
     * 查询城市字典表列表
     */
    @RequiresRoles("yw")
    @GetMapping("/list")
    public TableDataInfo list(DictCity dictCity)
    {
        startPage();
        List<DictCity> list = dictCityService.selectDictCityList(dictCity);
        return getDataTable(list);
    }

    /**
     * 导出城市字典表列表
     */
    @RequiresRoles("yw")
    @Log(title = "城市字典表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DictCity dictCity)
    {
        List<DictCity> list = dictCityService.selectDictCityList(dictCity);
        ExcelUtil<DictCity> util = new ExcelUtil<DictCity>(DictCity.class);
        util.exportExcel(response, list, "城市字典表数据");
    }

    /**
     * 获取城市字典表详细信息
     */
    @RequiresRoles("yw")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(dictCityService.selectDictCityById(id));
    }

    /**
     * 新增城市字典表
     */
    @RequiresRoles("yw")
    @Log(title = "城市字典表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DictCity dictCity)
    {
        return toAjax(dictCityService.insertDictCity(dictCity));
    }

    /**
     * 修改城市字典表
     */
    @RequiresRoles("yw")
    @Log(title = "城市字典表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DictCity dictCity)
    {
        return toAjax(dictCityService.updateDictCity(dictCity));
    }

    /**
     * 删除城市字典表
     */
    @RequiresRoles("yw")
    @Log(title = "城市字典表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dictCityService.deleteDictCityByIds(ids));
    }
}
