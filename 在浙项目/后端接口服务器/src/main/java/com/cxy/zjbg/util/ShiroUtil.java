package com.cxy.zjbg.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cxy.zjbg.shiro.AccountProfile;

import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static AccountProfile getProfile(){
        JSONObject json = JSONUtil.parseObj(SecurityUtils.getSubject().getPrincipal());
        System.out.println(json);
        return  JSONUtil.toBean(json,AccountProfile.class);
    }
}
