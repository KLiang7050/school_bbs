package com.bbs.user.adapter;

import com.bbs.common.core.domain.R;

import java.util.HashMap;

public interface UserTarget {
    R<HashMap<String, Object>> giteeLogin(String code, String state);
}
