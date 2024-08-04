package com.bbs.like.controller;

import com.bbs.common.core.domain.PageDomain;
import com.bbs.common.core.domain.R;
import com.bbs.common.core.domain.TableR;
import com.bbs.common.core.domain.like.entity.FavoritesUser;
import com.bbs.common.core.domain.like.entity.LikeUser;
import com.bbs.common.core.domain.post.dto.MainDTO;
import com.bbs.common.core.utils.JwtUtils;
import com.bbs.common.core.utils.web.AjaxResult;
import com.bbs.common.core.web.BaseController;
import com.bbs.like.service.IFavoritesUserService;
import com.bbs.like.service.ILikeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites/")
public class FavoritesUserController extends BaseController {

    @Autowired
    private IFavoritesUserService favoritesUserService;

    /**
     * @param favoriteUserId 被收藏贴子的作者
     * @return
     */
    @PostMapping("favorite/{postId}/{favoriteUserId}")
    public AjaxResult favorite(@PathVariable("postId") Long postId,
                                @PathVariable("favoriteUserId") Long favoriteUserId) {
        String userId = JwtUtils.getUserIdInHeader();
        boolean res = favoritesUserService.favorite(Long.valueOf(userId), Long.valueOf(postId.toString()),favoriteUserId);
        return AjaxResult.success(res?"收藏成功":"取消收藏");
    }

    @GetMapping("list")
    public AjaxResult list() {
        String userId = JwtUtils.getUserIdInHeader();
        List<FavoritesUser> list = favoritesUserService.list(userId);
        return AjaxResult.success(list);
    }

    @GetMapping("infolist")
    public TableR infoList() {
        String userId = JwtUtils.getUserIdInHeader();
        startPageNotOrder();
        List<MainDTO> list = favoritesUserService.infoList(userId);
        return getTableR(list);
    }
}
