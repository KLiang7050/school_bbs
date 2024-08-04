package com.bbs.api.user;

import com.bbs.common.core.domain.R;
import com.bbs.common.core.domain.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@FeignClient(contextId = "remoteUserService",name = "bbs-user-${school-name}")
public interface RemoteUserService {
    @GetMapping("/user/listbyids")
    R<List<User>> listByIds(@RequestParam("ids") Set<Long> ids);

    @GetMapping("/user/infobyid")
    R<User> infoById(@RequestParam("id") Long id);

    @PostMapping("/user/addPostCount")
    R addPostCount(@RequestParam("id") Long userId);

    @PostMapping("/user/addLikeCount")
    R addLikeCount(@RequestParam("id") Long userId);

    @PostMapping("/user/minusLikeCount")
    R minusLikeCount(@RequestParam("id") Long userId);

    @PostMapping("/user/addFavoriteCount")
    R addFavoriteCount(@RequestParam("id") Long userId);

    @PostMapping("/user/minusFavoriteCount")
    R minusFavoriteCount(@RequestParam("id") Long userId);

    @PostMapping("/user/minusPostCount")
    R minusPostCount(@RequestParam("id") Long userId);
}
