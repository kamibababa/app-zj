package com.cxy.zjbg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.constant.Constant;
import com.cxy.zjbg.common.lang.Result;
import com.cxy.zjbg.entity.Permission;
import com.cxy.zjbg.entity.Roles;
import com.cxy.zjbg.entity.Users;
import com.cxy.zjbg.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/list")
    public Result listPermission(){
        /**
         * @Author: xingyuchen
         * @Discription: 查询所有权限
         * @Date: 2021/7/19 11:58 上午
        */
        List<Permission> permissions = permissionService.list();
        if (permissions.size() > 0){
            return Result.succ(permissions);
        }
        return Result.fail("权限为空");
    }

    @GetMapping("/{pId}/get")
    public Result getPermissionById(@PathVariable(name = "pId")Integer pId){
        /**
         * @Author: xingyuchen
         * @Discription: 根据Id查询权限
         * @param pId 权限Id
         * @Date: 2021/7/19 11:58 上午
        */
        return Result.succ(permissionService.getById(pId));
    }

    @GetMapping("/{pId}/remove")
    public Result removePermissionById(@PathVariable(name = "pId")Integer pId){
        /**
         * @Author: xingyuchen
         * @Discription: 根据Id删除权限
         * @param pId
         * @Date: 2021/7/19 12:02 下午
        */
        return Result.succ(permissionService.removeById(pId));
    }

    @GetMapping("/list/with/search")
    public Result listWithSearch(@RequestParam(value = "page") Integer page,@RequestParam(value = "name") String name){
        /**
         * @Author: xingyuchen
         * @Discription: 查询分页或者模糊查询分页
         * @param page
         * @param name
         * @Date: 2021/8/14 7:22 下午
         */
        if ( !name.equals("") && !name.equals(null) ){
            Page pages = new Page(page, Constant.PAGE_RECORD);
            IPage<Users> page1 = permissionService.page(pages, new QueryWrapper<Permission>().like("p_name",name));
            return Result.succ(page1);
        }
        Page pages = new Page(page, Constant.PAGE_RECORD);
        //再在数据库里根据id正序排列下来
        IPage pageUsers = permissionService.page(pages, new QueryWrapper<Permission>().orderByAsc("p_id"));
        return Result.succ(pageUsers);
    }

    @PostMapping("/save")
    public Result savePermission(@RequestBody Permission permission){
        /**
         * @Author: xingyuchen
         * @Discription: 新增权限
         * @param permission
         * @Date: 2021/8/14 9:16 下午
        */
        boolean save = permissionService.save(permission);
        if(save){
            return Result.succ("新增成功");
        }
        return Result.fail("新增失败");
    }

    @PostMapping("/update")
    public Result updatePermission(@RequestBody Permission permission){
        /**
         * @Author: xingyuchen
         * @Discription: 修改权限信息｜必须要给rId不然就实现增的效果
         * @param permission
         * @Date: 2021/7/19 12:06 下午
        */
        boolean b = permissionService.saveOrUpdate(permission);
        if (b) {
            return Result.succ("修改成功");
        }
        return Result.fail("数据为空，修改失败");
    }
}

