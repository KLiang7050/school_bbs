package com.bbs.common.redis.CacheConstants;

public class UserCacheKey {

    public final static String USER = "user";

    public final static String SCHOOL = "school";

    public final static String CAPTCHA_CODE_KEY = "captcha_codes";

    public final static String USERINFO = "user_info";


    public static String userInfoKey(String token) {
        return USER+":"+USERINFO + ":" + token;
    }

    public static String captchaCodeKey(String uuid) {
        return USER+":"+CAPTCHA_CODE_KEY + ":" + uuid;
    }

    public static String SchoolListKey(){ return USER+":"+SCHOOL+":list";}
}
