package com.cxy.zjbg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxy.zjbg.common.dto.RoleToPermissionDto;
import com.cxy.zjbg.common.lang.Result;
import com.cxy.zjbg.entity.Permission;
import com.cxy.zjbg.entity.RoleToPermission;
import com.cxy.zjbg.entity.Roles;
import com.cxy.zjbg.service.PermissionService;
import com.cxy.zjbg.service.RoleToPermissionService;
import com.cxy.zjbg.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户权限表 前端控制器
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@RestController
@RequestMapping("/role-to-permission")
public class RoleToPermissionController {

    @Autowired
    private RoleToPermissionService roleToPermissionService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/list")
    public Result listRoleToPermission(){
        /**
         * @Author: xingyuchen
         * @Discription: 查询所有的角色权限表
         * @Date: 2021/7/19 7:47 下午
        */
        List<RoleToPermissionDto> roleToPermissionDtos = roleToPermissionService.listRoleToPermission();
        if(roleToPermissionDtos.size()>0){
            return Result.succ(roleToPermissionDtos);
        }
        return Result.fail("角色权限条数为空");
    }

    @GetMapping("/{rId}/get/tree")
    public Result getRoleToPermissionTree(@PathVariable(name = "rId")Integer rId){
        /**
         * @Author: xingyuchen
         * @Discription: 查询某一个角色的树形权限表
         * @param rId
         * @Date: 2021/8/15 12:31 下午
        */
        RoleToPermissionDto roleToPermissionDto = new RoleToPermissionDto();
        List<RoleToPermission> roles = roleToPermissionService.list(new QueryWrapper<RoleToPermission>().eq("r_id", rId));
        List<Permission> permissions=new ArrayList<>();
        for (RoleToPermission role : roles) {
            Permission byId = permissionService.getById(role.getPId());
            permissions.add(byId);
            roleToPermissionDto.setRId(role.getRId());
            Roles roleId = rolesService.getById(role.getRId());
            roleToPermissionDto.setRName(roleId.getRName());
        }
        roleToPermissionDto.setChildren(permissions);
        return Result.succ(roleToPermissionDto);
    }

    @GetMapping("/{pId}/get/permission/tree")
    public Result getPermissionTree(@PathVariable(name = "pId")Integer pId){
        RoleToPermissionDto roleToPermissionDto = new RoleToPermissionDto();
        List<RoleToPermission> roleToPermissions=roleToPermissionService.list(new QueryWrapper<RoleToPermission>().eq("p_id",pId));
        List<Roles> roles=new ArrayList<>();
        for (RoleToPermission roleToPermission:roleToPermissions) {
            Roles role = rolesService.getById(roleToPermission.getRId());
            roles.add(role);
            roleToPermissionDto.setPId(roleToPermission.getPId());
            Permission permission = permissionService.getById(roleToPermission.getPId());
            roleToPermissionDto.setPName(permission.getPName());
        }
        roleToPermissionDto.setChildren(roles);
        return Result.succ(roleToPermissionDto);
    }

    @GetMapping("/{rId}/get")
    public Result getRoleToPermission(@PathVariable(name = "rId") Integer rId){
        /**
         * @Author: xingyuchen
         * @Discription: 查询某一角色的所有权限
         * @param rId
         * @Date: 2021/7/19 7:48 下午
        */
        return Result.succ(roleToPermissionService.getRoleToPermissionById(rId));
    }

    @GetMapping("/list/tree")
    public Result listRoleToPermissionWithTree(){
        /**
         * @Author: xingyuchen
         * @Discription: 树形列表显示
         * @param
         * @Date: 2021/8/15 1:29 上午
        */
        List<Roles> list = rolesService.list();
        List<RoleToPermissionDto> roleToPermissionDtos=new ArrayList<>();
        for (Roles r:list) {
            RoleToPermissionDto roleToPermissionDto = new RoleToPermissionDto();
            roleToPermissionDto.setRId(r.getRId());
            roleToPermissionDto.setRName(r.getRName());
            List<Permission> permissions=new ArrayList<>();
            List<RoleToPermission> rId = roleToPermissionService.list(new QueryWrapper<RoleToPermission>().eq("r_id", r.getRId()));
            for (RoleToPermission roleToPermission:rId) {
                Permission byId = permissionService.getById(roleToPermission.getPId());
                permissions.add(byId);
            }
            roleToPermissionDto.setChildren(permissions);
            roleToPermissionDtos.add(roleToPermissionDto);
        }
        if( roleToPermissionDtos.size() > 0 ){
            return Result.succ(roleToPermissionDtos);
        }
        return Result.fail("无内容");
    }

    @GetMapping("/{rpId}/remove")
    public Result removeRoleToPermission(@PathVariable(name = "rpId") Integer rpId){
        /**
         * @Author: xingyuchen
         * @Discription: 删除某一ID的角色权限
         * @param rpId
         * @Date: 2021/7/19 8:52 下午
        */
        return Result.succ(roleToPermissionService.removeById(rpId));
    }

    @PostMapping("/upate")
    public Result updateRoleToPermission(@RequestBody RoleToPermission roleToPermission){
        /**
         * @Author: xingyuchen
         * @Discription: 修改某一角色权限
         * @param roleToPermission
         * @Date: 2021/7/19 9:38 下午
        */
        boolean b = roleToPermissionService.updateById(roleToPermission);
        if( b ) {
            return Result.succ("修改成功");
        }
        return  Result.fail("修改失败");
    }

    @PostMapping("/save")
    public Result saveRoleToPermission(@RequestBody RoleToPermission roleToPermission){
        /**
         * @Author: xingyuchen
         * @Discription: 给某一个角色一权限｜新增
         * @param roleToPermission
         * @Date: 2021/7/19 9:38 下午
        */
        boolean b = roleToPermissionService.save(roleToPermission);
        if( b ) {
            return Result.succ("增加成功");
        }
        return Result.fail("新增失败");
    }

}

