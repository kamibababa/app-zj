package com.cxy.zjbg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.constant.Constant;
import com.cxy.zjbg.common.lang.Result;
import com.cxy.zjbg.entity.Roles;
import com.cxy.zjbg.entity.Users;
import com.cxy.zjbg.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@RestController
@RequestMapping("/role")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping("/list")
    public Result listRoles(){
        /**
         * @Author: xingyuchen
         * @Discription: 查询所有的角色
         * @Date: 2021/7/19 10:52 上午
        */
        List<Roles> list = rolesService.list();
        if(list.size() > 0) {
            return Result.succ(list);
        }
        return Result.fail("角色条数为空");
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
            IPage<Users> page1 = rolesService.page(pages, new QueryWrapper<Roles>().like("r_name",name));
            return Result.succ(page1);
        }
        Page pages = new Page(page, Constant.PAGE_RECORD);
        //再在数据库里根据id正序排列下来
        IPage pageUsers = rolesService.page(pages, new QueryWrapper<Roles>().orderByAsc("r_id"));
        return Result.succ(pageUsers);
    }

    @GetMapping("/{rId}/get")
    public Result getRoleById(@PathVariable(name = "rId")Integer rId){
        /**
         * @Author: xingyuchen
         * @Discription: 根据ID查询角色信息
         * @param rId
         * @Date: 2021/7/19 10:56 上午
        */
        return Result.succ(rolesService.getById(rId));
    }

    @GetMapping("/{rId}/remove")
    public Result removeRoleById(@PathVariable(name = "rId") Integer rId) {
        /**
         * @Author: xingyuchen
         * @Discription: 根绝Id移除角色
         * @param rId
         * @Date: 2021/7/19 10:58 上午
        */
        return Result.succ(rolesService.removeById(rId));
    }

    @PostMapping("/update")
    public Result updateRole(@RequestBody Roles roles){
        /**
         * @Author: xingyuchen
         * @Discription: 修改角色信息｜必须要给rId不然就实现增的效果
         * @param roles
         * @Date: 2021/7/19 11:07 上午
        */
        boolean b = rolesService.saveOrUpdate(roles);
        if (b){
            return Result.succ("修改成功");
        }
        return Result.fail("修改失败");
    }

    @PostMapping("/save")
    public Result saveRole(@RequestBody Roles roles){
        /**
         * @Author: xingyuchen
         * @Discription: 新增角色
         * @param roles
         * @Date: 2021/7/19 11:09 上午
        */
        boolean b = rolesService.saveOrUpdate(roles);
        if(b) {
           return Result.succ("添加成功");
        }
        return Result.fail("添加失败");
    }
}

