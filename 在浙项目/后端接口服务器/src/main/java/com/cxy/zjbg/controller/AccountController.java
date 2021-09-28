package com.cxy.zjbg.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxy.zjbg.common.constant.Constant;
import com.cxy.zjbg.common.dto.LoginDto;
import com.cxy.zjbg.common.dto.UserDto;
import com.cxy.zjbg.common.lang.Result;
import com.cxy.zjbg.entity.Users;
import com.cxy.zjbg.service.UsersService;
import com.cxy.zjbg.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    private UsersService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        System.out.println(loginDto);
        Users user = userService.getOne(new QueryWrapper<Users>().eq("u_email", loginDto.getUEmail()));
        System.out.println(user);
        //判断用户为不为空，如果为空就是用户不存在
        Assert.notNull(user, "用户不存在");
        //需要加密解密操作
        if (!user.getUPassword().equals(loginDto.getUPassword())) {
            return Result.fail("密码不正确");
        }
        UserDto userById = userService.getUserById(user.getUId().intValue());
        String jwt = jwtUtils.generateToken(user.getUId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return Result.succ(MapUtil.builder()
                .put("uId", user.getUId())
                .put("uUsername",user.getUUsername())
                .put("uEmail",user.getUEmail())
                .put("uSex",user.getUSex())
                .put("uTelephone",user.getUTelephone())
                .put("uRoleId",user.getURoleId())
                .put("rName",userById.getRName())
                .put("deleted",user.getDeleted())
                .put("gmt_create",user.getGmtCreate())
                .put("gmt_modified",user.getGmtModified())
                .put("status",user.getStatus())
                .map());

    }

    //认证之后权限
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        System.out.println("退出");
        SecurityUtils.getSubject().logout();
        return Result.succ(Constant.SUCCESS_CODE,"退出成功",null);
    }
}
