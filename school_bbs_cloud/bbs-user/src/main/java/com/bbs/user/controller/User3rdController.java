package com.bbs.user.controller;

import com.bbs.common.core.domain.R;
import com.bbs.common.core.exception.BizException;
import com.bbs.user.adapter.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/3rd/")
public class User3rdController {

    @Autowired
    UserAdapter userAdapter;

    @Value("${gitee.url.authUrl}")
    private String giteeAuthUrl;

    @Value("${school-name}")
    private String schoolName;

    @ResponseBody
    @GetMapping("/goGitee")
    public String goGitee() {
        return giteeAuthUrl;
    }

    @GetMapping("/gitee")
    public String giteeLogin(String code, String state) throws BizException {
        R<HashMap<String, Object>> r = userAdapter.giteeLogin(code, state);
        return "redirect:http://www.schoolbbs-".concat(schoolName).concat(".com").concat("/homepage?token=").concat(r.getData().get("token").toString());
        // return "redirect:http://localhost:3000/homepage?token=".concat(r.getData().get("token").toString());
    }



}