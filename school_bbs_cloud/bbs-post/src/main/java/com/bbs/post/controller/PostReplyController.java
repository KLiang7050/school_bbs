package com.bbs.post.controller;

import com.bbs.common.core.domain.R;
import com.bbs.common.core.domain.TableR;
import com.bbs.common.core.domain.post.dto.ReplyDTO;
import com.bbs.common.core.domain.post.entity.PostReply;
import com.bbs.common.core.domain.post.vo.PostAddVO;
import com.bbs.common.core.utils.JwtUtils;
import com.bbs.common.core.utils.web.AjaxResult;
import com.bbs.common.core.web.BaseController;
import com.bbs.post.service.IPostReplyService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;


@RestController
@RequestMapping("/reply")
public class PostReplyController extends BaseController {

    @Autowired
    private IPostReplyService replyService;

    @GetMapping("myReply")
    public TableR getMyReply() {
        Long userId = Long.valueOf(JwtUtils.getUserIdInHeader());
        startPageNotOrder();
        return replyService.getMyReply(userId);
    }

    @GetMapping("list")
    public TableR replyList(@RequestParam("mainId") String mainId,
                            @RequestParam("orderByColumn") String orderByColumn) {
        // 由于sql编译使用了${}，所以这里需要进行校验，保证不出现sql注入的情况
        if(!orderByColumn.trim().equals("create_time") &&
                !orderByColumn.trim().equals("likes")){
            TableR r = new TableR();
            r.setCode(500);
            r.setMsg("排序字段不合法");
            //TODO 暂时这么写，后面看看能不能用数据校验解决
            return r;
        }

        startPageNotOrder();
        return replyService.getList(Long.valueOf(mainId), orderByColumn);
    }

    @GetMapping("morelist")
    public TableR replyList(@RequestParam("rootId") Long rootId, @RequestParam("orderByColumn") String orderByColumn) {
        // 由于sql编译使用了${}，所以这里需要进行校验，保证不出现sql注入的情况
        if(!orderByColumn.trim().equals("create_time") &&
                !orderByColumn.trim().equals("likes")){
            TableR r = new TableR();
            r.setCode(500);
            r.setMsg("排序字段不合法");
        }

        startPageNotOrder();
        return getTableR(replyService.getReply2List(rootId,orderByColumn));
    }

    @PostMapping("addReply")
    public AjaxResult addReply(@RequestBody PostAddVO replyDatas, @RequestParam("mainId") Long mainId, @RequestParam("parentUserId") Long parentUserId) {
        Long userId = Long.valueOf(JwtUtils.getUserIdInHeader());
        int row = replyService.addReply(replyDatas, userId, mainId, parentUserId);
        return row > 0 ? success() : fail();
    }

    @PostMapping("addReply2")
    public AjaxResult addReply2(@RequestBody PostReply reply, @RequestParam("content") String content) {
        Long userId = Long.valueOf(JwtUtils.getUserIdInHeader());
        int row = replyService.addReply2(reply, content, userId);
        return row > 0 ? success() : fail();
    }

    @PostMapping("addLikeCount")
    public R addReplyLikeCount(@RequestParam("postId") Long postId){
        boolean res = replyService.addLikeById(postId);
        return res?R.ok():R.fail();
    }

    @PostMapping("minusLikeCount")
    R minusReplyLikeCount(@RequestParam("postId") Long postId){
        boolean res = replyService.minusLikeCount(postId);
        return res?R.ok():R.fail();
    }
}
