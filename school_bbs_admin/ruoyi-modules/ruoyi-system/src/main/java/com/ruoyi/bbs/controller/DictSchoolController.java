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
import com.ruoyi.bbs.domain.DictSchool;
import com.ruoyi.bbs.service.IDictSchoolService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 学校字典表Controller
 * 
 * @author ckl
 * @date 2024-04-05
 */
@RestController
@RequestMapping("/app/school")
public class DictSchoolController extends BaseController
{
    @Autowired
    private IDictSchoolService dictSchoolService;

    /**
     * 查询学校字典表列表
     */
    @RequiresRoles("yw")
    @GetMapping("/list")
    public TableDataInfo list(DictSchool dictSchool)
    {
        startPage();
        List<DictSchool> list = dictSchoolService.selectDictSchoolList(dictSchool);
        return getDataTable(list);
    }

    /**
     * 导出学校字典表列表
     */
    @RequiresRoles("yw")
    @Log(title = "学校字典表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DictSchool dictSchool)
    {
        List<DictSchool> list = dictSchoolService.selectDictSchoolList(dictSchool);
        ExcelUtil<DictSchool> util = new ExcelUtil<DictSchool>(DictSchool.class);
        util.exportExcel(response, list, "学校字典表数据");
    }

    /**
     * 获取学校字典表详细信息
     */
    @RequiresRoles("yw")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(dictSchoolService.selectDictSchoolById(id));
    }

    /**
     * 新增学校字典表
     */
    @RequiresRoles("yw")
    @Log(title = "学校字典表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DictSchool dictSchool)
    {
        return toAjax(dictSchoolService.insertDictSchool(dictSchool));
    }

    /**
     * 修改学校字典表
     */
    @RequiresRoles("yw")
    @Log(title = "学校字典表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DictSchool dictSchool)
    {
        return toAjax(dictSchoolService.updateDictSchool(dictSchool));
    }

    /**
     * 删除学校字典表
     */
    @RequiresRoles("yw")
    @Log(title = "学校字典表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dictSchoolService.deleteDictSchoolByIds(ids));
    }
}
