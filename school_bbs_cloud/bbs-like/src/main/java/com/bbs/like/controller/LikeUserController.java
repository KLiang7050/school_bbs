package com.bbs.like.controller;

import com.bbs.common.core.domain.like.entity.LikeUser;
import com.bbs.common.core.utils.JwtUtils;
import com.bbs.common.core.utils.web.AjaxResult;
import com.bbs.like.service.ILikeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/like/")
public class LikeUserController {

    @Autowired
    private ILikeUserService likeUserService;

    @PostMapping("like/{postId}/{type}/{mainId}/{likeUserId}")
    public AjaxResult like(@PathVariable("postId") Long postId,
                           @PathVariable("type") String type,
                           @PathVariable("mainId") Long mainId,
                           @PathVariable("likeUserId") Long likeUserId) {
        String userId = JwtUtils.getUserIdInHeader();
        boolean res = likeUserService.like(Long.valueOf(userId), Long.valueOf(postId.toString()),type,mainId,likeUserId);
        return AjaxResult.success(res?"点赞成功":"取消点赞");
    }

    @GetMapping("list/{mainId}")
    public AjaxResult list(@PathVariable("mainId") Long mainId) {
        String userId = JwtUtils.getUserIdInHeader();
        List<LikeUser> list = likeUserService.list(userId, mainId);
        return AjaxResult.success(list);
    }

}
