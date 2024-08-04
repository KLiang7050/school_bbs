package com.ruoyi.bbs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.JwtUtils;
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
import com.ruoyi.bbs.domain.Requisition;
import com.ruoyi.bbs.service.IRequisitionService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 需求管理Controller
 * 
 * @author ckl
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/app/requisition")
public class RequisitionController extends BaseController
{
    @Autowired
    private IRequisitionService requisitionService;

    /**
     * 查询需求管理列表
     */
    @RequiresPermissions("bbs:requisition:list")
    @GetMapping("/list")
    public TableDataInfo list(Requisition requisition)
    {
        startPage();
        List<Requisition> list = requisitionService.selectRequisitionList(requisition);
        return getDataTable(list);
    }

    /**
     * 导出需求管理列表
     */
    @RequiresPermissions("bbs:requisition:export")
    @Log(title = "需求管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Requisition requisition)
    {
        List<Requisition> list = requisitionService.selectRequisitionList(requisition);
        ExcelUtil<Requisition> util = new ExcelUtil<Requisition>(Requisition.class);
        util.exportExcel(response, list, "需求管理数据");
    }

    /**
     * 获取需求管理详细信息
     */
    @RequiresPermissions("bbs:requisition:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(requisitionService.selectRequisitionById(id));
    }

    /**
     * 新增需求管理
     */
    @RequiresPermissions("bbs:requisition:add")
    @Log(title = "需求管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Requisition requisition)
    {
        return toAjax(requisitionService.insertRequisition(requisition));
    }

    /**
     * 修改需求管理
     */
    @RequiresPermissions("bbs:requisition:edit")
    @Log(title = "需求管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Requisition requisition)
    {
        return toAjax(requisitionService.updateRequisition(requisition));
    }

    /**
     * 删除需求管理
     */
    @RequiresPermissions("bbs:requisition:remove")
    @Log(title = "需求管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(requisitionService.deleteRequisitionByIds(ids));
    }
}
