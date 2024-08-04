package com.bbs.api.req;

import com.bbs.common.core.domain.user.entity.Joinus;
import com.bbs.common.core.utils.web.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(contextId = "remoteJoinUsService",name = "ruoyi-system")
public interface RemoteJoinUsService {

    @PostMapping
    AjaxResult add(@RequestBody Joinus joinus);
}
