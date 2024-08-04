package com.bbs.post.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bbs.common.core.domain.TableR;
import com.bbs.common.core.domain.post.dto.MainDTO;
import com.bbs.common.core.domain.post.entity.PostMain;
import com.bbs.common.core.domain.post.vo.PostAddVO;

import java.util.List;

public interface IPostMainService extends IService<PostMain>
{
    Long addMain(PostAddVO postAddVO, Long userId);

    MainDTO mainInfo(Long id);


    TableR getList(String title, Integer typeId, String orderByColumn);

    List<MainDTO> getHots();

    boolean addLikeById(Long id);

    boolean minusLikeCount(Long postId);

    boolean addFavoritesById(Long postId);

    boolean minusFavoritesById(Long postId);

    List<MainDTO> list(List<Long> idList);

    TableR getMyPostList(Long userId);

    boolean update(PostAddVO postAddVO);

    boolean delById(Long id, String userId);

    Integer updatePostFlagByContentIdList(List<Long> okContentIdList);
}
