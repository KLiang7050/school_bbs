package com.bbs.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bbs.common.core.domain.user.entity.Joinus;
import com.bbs.common.core.domain.user.vo.JoinusVo;

import java.util.List;

/**
 * 用户Service接口
 * 
 * @author ckl
 * @date 2023-10-03
 */
public interface IJoinusService extends IService<Joinus>
{

    List<JoinusVo> list(String userId);

    void saveJoinus(Joinus joinus);

    JoinusVo one(Long id);

    boolean commitJoinus(Joinus joinus);
}
