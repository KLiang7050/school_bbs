package com.bbs.common.core.utils.post;


import com.bbs.common.core.domain.post.dto.ReplyDTO;
import com.bbs.common.core.domain.post.entity.PostContent;
import com.bbs.common.core.domain.user.entity.User;
import com.bbs.common.core.utils.user.UserUtils;

import java.util.ArrayList;
import java.util.List;

public class PostUtils {

    public static List<ReplyDTO> getReplyList(List<ReplyDTO> reply2List, Long postId, List<User> userList) {
        // 获取当前一级回复下的第一个二级回复贴的索引
        int firstReplyIndex = getFirstReplyIndex(reply2List, postId);
        if (firstReplyIndex == -1) {
            return null;
        }
        // 整理二级回复贴
        List<ReplyDTO> replyList = new ArrayList<>();
        for (int i = firstReplyIndex; i < reply2List.size(); i++) {
            ReplyDTO post = reply2List.get(i);
            if (post == null || !post.getRootId().equals(postId)) {
                break;
            }
            User user = UserUtils.getUser(userList, post.getUserId());
            post.setUser(user);
            replyList.add(post);
        }
        return replyList;
    }

    public static int getFirstReplyIndex(List<ReplyDTO> dtoList, Long postId) {
        int i = 0, j = dtoList.size() - 1, mid = 0;
        while (i <= j) {
            mid = i + (j - i) / 2;
            Long rootId = dtoList.get(mid).getRootId();
            if (rootId.equals(postId)) {
                // 检测是左边否有重复值
                while (mid > 0 && dtoList.get(mid - 1).getRootId().equals(postId)) {
                    mid--;
                }
                return mid;
            } else if (rootId < postId) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return -1;
    }

    public static int getParent(List<ReplyDTO> reply, Long parentId) {
        int i = 0, j = reply.size() - 1, mid = 0;
        while (i <= j) {
            mid = i + (j - i) / 2;
            Long id = reply.get(mid).getId();
            if (id.equals(parentId)) {
                return mid;
            } else if (id < parentId) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return -1;
    }



    private static PostContent getContent(List<PostContent> contentList, Long contentId) {
        int i = 0, j = contentList.size() - 1, mid = 0;
        while (i < j) {
            mid = i + (j - i) / 2;
            // if (contentList.get(mid).getId().equals(contentId)) {
            //     i = mid + 1;
            // } else {
            //     j = mid;
            // }
            Long id = contentList.get(mid).getId();
            if (id.equals(contentId)) {
                break;
            } else if (id < contentId) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return contentList.get(mid);
    }
}
