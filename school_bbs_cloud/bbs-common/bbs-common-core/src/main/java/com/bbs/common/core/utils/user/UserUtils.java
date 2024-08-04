package com.bbs.common.core.utils.user;

import com.bbs.common.core.domain.user.entity.User;

import java.util.List;

public class UserUtils {

    public static User getUser(List<User> userList, Long userId) {
        int i = 0, j = userList.size() - 1, mid = 0;
        while (i <= j) {
            mid = i + (j - i) / 2;
            Long id = userList.get(mid).getId();
            if (id.equals(userId)) {
                break;
            } else if (id < userId) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return userList.get(mid) == null? null : userList.get(mid);
    }

}
