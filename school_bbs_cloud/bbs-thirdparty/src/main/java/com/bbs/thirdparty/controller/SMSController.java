package com.bbs.thirdparty.controller;

import com.bbs.common.core.exception.BizException;
import com.bbs.common.core.exception.ReqPathException;
import com.bbs.common.core.utils.RegexUtils;
import com.bbs.common.core.utils.web.AjaxResult;
import com.bbs.thirdparty.component.sms.SMSComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms/")
public class SMSController {

    @Autowired
    private SMSComponent smsComponent;

    @GetMapping("sendcode/{phone}")
    public AjaxResult sendCode(@PathVariable("phone") String phone) throws BizException, ReqPathException {
        if (RegexUtils.isPhoneInvalid(phone)) {
            throw new ReqPathException("手机号错误");
        }
        return smsComponent.createCaptchaToPhone(phone);
    }
}
