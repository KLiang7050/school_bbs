package com.bbs.api.post;

import com.bbs.common.core.domain.R;
import com.bbs.common.core.domain.post.dto.DetectionLogDTO;
import com.bbs.common.core.domain.post.dto.MainDTO;
import com.bbs.common.core.domain.post.entity.DetectionLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(contextId = "remotePostService", name = "bbs-post-${school-name}")
public interface RemotePostService {
    @PostMapping("/main/addLikeCount")
    R addMainLikeCount(@RequestParam("postId") Long postId);

    @PostMapping("/main/minusLikeCount")
    R minusMainLikeCount(@RequestParam("postId") Long postId);

    @PostMapping("/reply/addLikeCount")
    R addReplyLikeCount(@RequestParam("postId") Long postId);

    @PostMapping("/reply/minusLikeCount")
    R minusReplyLikeCount(@RequestParam("postId") Long postId);

    @PostMapping("/main/addFavoriteCount")
    R addMainFavoritesCount(@RequestParam("postId") Long postId);

    @PostMapping("/main/minusFavoriteCount")
    R minusFavoritesCount(@RequestParam("postId") Long postId);

    @GetMapping("/main/listbyid")
    R<List<MainDTO>> listbyid(@RequestParam("idList")List<Long> idList);

}
