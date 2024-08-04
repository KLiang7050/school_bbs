package com.ruoyi.bbs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.bbs.domain.dto.MainDTO;
import com.ruoyi.bbs.domain.dto.ReplyDTO;
import com.ruoyi.bbs.service.IPostReplyService;
import com.ruoyi.common.security.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.bbs.domain.PostMain;
import com.ruoyi.bbs.service.IPostMainService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 贴子主贴Controller
 *
 * @author ckl
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/app/main")
public class PostMainController extends BaseController {
    @Autowired
    private IPostMainService postMainService;
    @Autowired
    private IPostReplyService postReplyService;


    @RequiresPermissions("bbs:main:unaudited")
    @GetMapping("/unaudited")
    public TableDataInfo unaudited(@RequestParam("pageNum") Integer pageNum,
                                   @RequestParam("pageSize") Integer pageSize) {
        Integer startNum = (pageNum - 1) * pageSize;
        List<MainDTO> mainList = postMainService.unaudited(startNum,pageSize);
        TableDataInfo tableDataInfo = new TableDataInfo(mainList,mainList.size());
        tableDataInfo.setCode(200);
        tableDataInfo.setMsg("查询成功");
        return tableDataInfo;
    }

    /**
     * 查询贴子主贴列表
     */
    @RequiresPermissions("bbs:main:list")
    @GetMapping("/list")
    public TableDataInfo list(PostMain postMain)
    {
        startPage();
        List<PostMain> list = postMainService.selectPostMainList(postMain);
        return getDataTable(list);
    }

    /**
     * 导出贴子主贴列表
     */
    @RequiresPermissions("bbs:main:export")
    @Log(title = "贴子主贴", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PostMain postMain)
    {
        List<PostMain> list = postMainService.selectPostMainList(postMain);
        ExcelUtil<PostMain> util = new ExcelUtil<PostMain>(PostMain.class);
        util.exportExcel(response, list, "贴子主贴数据");
    }

    /**
     * 获取贴子主贴详细信息
     */
    @RequiresPermissions("bbs:main:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(postMainService.selectPostMainById(id));
    }

    /**
     * 新增贴子主贴
     */
    @RequiresPermissions("bbs:main:add")
    @Log(title = "贴子主贴", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PostMain postMain)
    {
        return toAjax(postMainService.insertPostMain(postMain));
    }

    /**
     * 修改贴子主贴
     */
    @RequiresPermissions("bbs:main:edit")
    @Log(title = "贴子主贴", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PostMain postMain)
    {
        return toAjax(postMainService.updatePostMain(postMain));
    }

    /**
     * 删除贴子主贴
     */
    @RequiresPermissions("bbs:main:remove")
    @Log(title = "贴子主贴", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(postMainService.deletePostMainByIds(ids));
    }
}
