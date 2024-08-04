package com.bbs.post.controller;

import com.bbs.common.core.domain.R;
import com.bbs.common.core.domain.TableR;
import com.bbs.common.core.domain.post.dto.MainDTO;
import com.bbs.common.core.domain.post.entity.DetectionLog;
import com.bbs.common.core.domain.post.vo.PostAddVO;
import com.bbs.common.core.utils.JwtUtils;
import com.bbs.common.core.utils.web.AjaxResult;
import com.bbs.common.core.web.BaseController;
import com.bbs.post.service.DetectionLogService;
import com.bbs.post.service.IPostMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main/")
public class PostMainController extends BaseController {

    @Autowired
    IPostMainService postMainService;
    @Autowired
    DetectionLogService detectionLogService;

    @GetMapping("list")
    public TableR mainList(@RequestParam(value = "typeId", required = false) Integer typeId,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam("orderByColumn") String orderByColumn) {
        typeId = typeId == null ? 0 : typeId;
        startPageNotOrder();
        return postMainService.getList(title, typeId, orderByColumn);
    }

    @GetMapping("getMyPostList")
    public TableR getMyPostList() {
        Long userId = Long.valueOf(JwtUtils.getUserIdInHeader());
        startPageNotOrder();
        return postMainService.getMyPostList(userId);
    }

    @PostMapping("addMain")
    public AjaxResult addMain(@RequestBody PostAddVO postAddVO) {
        Long userId = Long.valueOf(JwtUtils.getUserIdInHeader());
        Long id = postMainService.addMain(postAddVO, userId);
        return success().put("id",id);
    }

    @GetMapping("info/{id}")
    public R<MainDTO> mainInfo(@PathVariable("id") String id) {
        MainDTO vo = postMainService.mainInfo(Long.valueOf(id));
        return R.ok(vo);
    }

    @GetMapping("hots")
    public AjaxResult getHots() {
        List<MainDTO> list = postMainService.getHots();
        return AjaxResult.success(list);
    }

    @PostMapping("addLikeCount")
    public R addMainLikeCount(@RequestParam("postId") Long postId) {
        boolean res = postMainService.addLikeById(postId);
        return res ? R.ok() : R.fail();
    }

    @PostMapping("minusLikeCount")
    public R minusLikeCount(@RequestParam("postId") Long postId) {
        boolean res = postMainService.minusLikeCount(postId);
        return res ? R.ok() : R.fail();
    }

    @PostMapping("addFavoriteCount")
    public R addMainFavoritesCount(@RequestParam("postId") Long postId) {
        boolean res = postMainService.addFavoritesById(postId);
        return res ? R.ok() : R.fail();
    }

    @PostMapping("minusFavoriteCount")
    public R minusFavoriteCount(@RequestParam("postId") Long postId) {
        boolean res = postMainService.minusFavoritesById(postId);
        return res ? R.ok() : R.fail();
    }

    @GetMapping("listbyid")
    R<List<MainDTO>> listbyid(@RequestParam("idList") List<Long> idList) {
        return R.ok(postMainService.list(idList));
    }

    @PostMapping("updatePost")
    public AjaxResult updatePost(@RequestBody PostAddVO postAddVO){
        boolean res = postMainService.update(postAddVO);
        return res?success():error();
    }

    @PostMapping("delPost/{id}")
    public AjaxResult delPost(@PathVariable("id") Long id){
        String userId = JwtUtils.getUserIdInHeader();
        boolean res = postMainService.delById(id,userId);
        return res ? success():error();
    }

    @PostMapping("/main/updatePostFlagByContentIdList")
    Integer updatePostFlagByContentIdList(List<Long> okContentIdList){
        return postMainService.updatePostFlagByContentIdList(okContentIdList);
    }
}
