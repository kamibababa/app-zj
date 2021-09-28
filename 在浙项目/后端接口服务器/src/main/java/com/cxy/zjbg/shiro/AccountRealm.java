package com.cxy.zjbg.shiro;

import cn.hutool.core.bean.BeanUtil;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxy.zjbg.entity.Permission;
import com.cxy.zjbg.entity.RoleToPermission;
import com.cxy.zjbg.entity.Roles;
import com.cxy.zjbg.entity.Users;
import com.cxy.zjbg.mapper.PermissionMapper;
import com.cxy.zjbg.service.PermissionService;
import com.cxy.zjbg.service.RoleToPermissionService;
import com.cxy.zjbg.service.RolesService;
import com.cxy.zjbg.service.UsersService;
import com.cxy.zjbg.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UsersService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleToPermissionService roleToPermissionService;

    @Autowired
    private RolesService rolesService;

    //告诉它支持的token类型是jwttoken,而非其他的token
    //让realm支持jwt的凭证校验
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //获取权限
    //权限校验
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("------开始添加权限");
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        AccountProfile user = (AccountProfile) principalCollection.getPrimaryPrincipal();
        //2.添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Roles role = rolesService.getById(user.getURoleId());
        //添加角色
        simpleAuthorizationInfo.addRole(role.getRName());
        //添加权限
        List<RoleToPermission> roleToPermissions = roleToPermissionService.list(new QueryWrapper<RoleToPermission>().eq("r_id", user.getURoleId()));
        for (RoleToPermission roleToPermission:roleToPermissions) {
            Permission permission = permissionService.getById(roleToPermission.getPId());
            simpleAuthorizationInfo.addStringPermission(permission.getPName());
        }
        System.out.println("------结束添加权限");
        return simpleAuthorizationInfo;
    }

    //身份验证
    //登陆认证校验
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //登陆逻辑开发
        // 调用 subject 的登陆会走这个校验
        JwtToken jwtToken=(JwtToken)authenticationToken;
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        Users user = userService.getById(Long.valueOf(userId));
        if(user == null){
            throw new UnknownAccountException("账户不存在");
        }
        if(user.getStatus()==-1){
            //如果账户被锁定
            throw new LockedAccountException("账户已被锁定");
        }
        AccountProfile profile=new AccountProfile();
        //把公开的用户信息copy到profile里
        BeanUtil.copyProperties(user,profile);

        System.out.println("------");
        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }
}
