package com.ruoyi.bbs.controller;

import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.bbs.service.IFavoritesUserService;
import com.ruoyi.bbs.service.ILikeUserService;
import com.ruoyi.bbs.service.IPostMainService;
import com.ruoyi.common.security.annotation.RequiresRoles;
import io.swagger.models.auth.In;
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
import com.ruoyi.bbs.domain.User;
import com.ruoyi.bbs.service.IUserService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 用户Controller
 *
 * @author ckl
 * @date 2024-04-05
 */
@RestController
@RequestMapping("/app/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IPostMainService mainService;
    @Autowired
    private ILikeUserService likeUserService;
    @Autowired
    private IFavoritesUserService favoritesUserService;

    /**
     * 查询用户列表
     */
    @RequiresPermissions("bbs:user:list")
    @GetMapping("/list")
    public TableDataInfo list(User user)
    {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @RequiresPermissions("bbs:user:export")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, User user)
    {
        List<User> list = userService.selectUserList(user);
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        util.exportExcel(response, list, "用户数据");
    }

    /**
     * 获取用户详细信息
     */
    @RequiresPermissions("bbs:user:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userService.selectUserById(id));
    }

    /**
     * 新增用户
     */
    @RequiresPermissions("bbs:user:add")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody User user)
    {
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @RequiresPermissions("bbs:user:edit")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody User user)
    {
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @RequiresPermissions("bbs:user:remove")
    @Log(title = "用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userService.deleteUserByIds(ids));
    }

    @GetMapping("/getIndexCount")
    public AjaxResult getIndexCount() {
        Integer allUserCount = userService.selectCount();
        Integer onlineUserCount = userService.selectonlineUserCount();

        Integer todayNewPost = mainService.selectTodayNewPost();

        Integer todayLike = likeUserService.selectTodayCount();
        Integer todayFavorites = favoritesUserService.selectTodayCount();
        Integer todayActive = todayNewPost + todayLike + todayFavorites;

        HashMap<String, Object> map = new HashMap<>();
        map.put("allUserCount", allUserCount);
        map.put("onlineUserCount", onlineUserCount);
        map.put("todayNewPost", todayNewPost);
        map.put("todayActive", todayActive);
        return success(map);
    }
}
