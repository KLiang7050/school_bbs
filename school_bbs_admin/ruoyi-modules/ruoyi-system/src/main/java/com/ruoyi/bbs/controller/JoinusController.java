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
import com.ruoyi.bbs.domain.Joinus;
import com.ruoyi.bbs.service.IJoinusService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 入驻申请Controller
 * 
 * @author ckl
 * @date 2024-04-05
 */
@RestController
@RequestMapping("/app/joinus")
public class JoinusController extends BaseController
{
    @Autowired
    private IJoinusService joinusService;

    /**
     * 查询入驻申请列表
     */
    @RequiresPermissions("bbs:joinus:list")
    @GetMapping("/list")
    public TableDataInfo list(Joinus joinus)
    {
        startPage();
        List<Joinus> list = joinusService.selectJoinusList(joinus);
        return getDataTable(list);
    }

    /**
     * 导出入驻申请列表
     */
    @RequiresPermissions("bbs:joinus:export")
    @Log(title = "入驻申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Joinus joinus)
    {
        List<Joinus> list = joinusService.selectJoinusList(joinus);
        ExcelUtil<Joinus> util = new ExcelUtil<Joinus>(Joinus.class);
        util.exportExcel(response, list, "入驻申请数据");
    }

    /**
     * 获取入驻申请详细信息
     */
    @RequiresPermissions("bbs:joinus:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(joinusService.selectJoinusById(id));
    }

    /**
     * 新增入驻申请
     */
    @Log(title = "入驻申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Joinus joinus)
    {
        return toAjax(joinusService.insertJoinus(joinus));
    }

    /**
     * 修改入驻申请
     */
    @RequiresPermissions("bbs:joinus:edit")
    @Log(title = "入驻申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Joinus joinus)
    {
        return toAjax(joinusService.updateJoinus(joinus));
    }

    /**
     * 删除入驻申请
     */
    @RequiresPermissions("bbs:joinus:remove")
    @Log(title = "入驻申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(joinusService.deleteJoinusByIds(ids));
    }
}
