package com.ruoyi.bbs.controller;

import com.ruoyi.bbs.domain.DetectionLog;
import com.ruoyi.bbs.domain.dto.DetectionLogDTO;
import com.ruoyi.bbs.domain.dto.MainDTO;
import com.ruoyi.bbs.service.DetectionLogService;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/detectionlog")
public class DetectionLogController {

    @Autowired
    private DetectionLogService detectionLogService;

    @RequiresPermissions("bbs:detectionlog:list")
    @GetMapping("/getOutList")
    public TableDataInfo getOutList(@RequestParam("pageNum") Integer pageNum,
                                   @RequestParam("pageSize") Integer pageSize) {
        Integer startNum = (pageNum - 1) * pageSize;
        List<DetectionLogDTO> logList = detectionLogService.getOutList(startNum,pageSize);
        TableDataInfo tableDataInfo = new TableDataInfo(logList,logList.size());
        tableDataInfo.setCode(200);
        tableDataInfo.setMsg("查询成功");
        return tableDataInfo;
    }

    @RequiresPermissions("bbs:detectionlog:pass")
    @PostMapping("/pass")
    public AjaxResult pass(@RequestParam("id") Long id) {
        boolean res =  detectionLogService.pass(id);
        return res?AjaxResult.success():AjaxResult.error();
    }

    @RequiresPermissions("bbs:detectionlog:ban")
    @PostMapping("/ban")
    public AjaxResult ban(@RequestParam("id") Long id) {
        boolean res =  detectionLogService.ban(id);
        return res?AjaxResult.success():AjaxResult.error();
    }
}