package com.cxy.zjbg.controller;


import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.constant.Constant;
import com.cxy.zjbg.common.dto.*;
import com.cxy.zjbg.common.lang.Result;
import com.cxy.zjbg.entity.RoleToPermission;
import com.cxy.zjbg.entity.Roles;
import com.cxy.zjbg.entity.Users;
import com.cxy.zjbg.service.RoleToPermissionService;
import com.cxy.zjbg.service.RolesService;
import com.cxy.zjbg.service.UsersService;
import com.cxy.zjbg.util.EmailUtil;
import com.cxy.zjbg.util.MenuUtil;
import com.cxy.zjbg.util.RedisActionUtil;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RoleToPermissionService roleToPermissionService;

    @Autowired
    private RedisActionUtil redisActionUtil;

    @Autowired
    private RolesService rolesService;

    /**
     * @Author: xingyuchen
     * @Discription: 以下的方法用于注册验证以及密码找回
     * @Methods sentEmail 邮箱验证 /email/sent
     * @Methods register 邮箱注册用户 /register
     * @Methods sentEmailPassword 找回密码验证邮件  /email/sent/password
     * @Methods findEmailPassword 找回邮箱密码邮件  /email/find/password
     * @Date: 2021/7/17 12:08 上午
    */

    @PostMapping("/email/sent")
    public Result sentEmail(@RequestBody EmailDto emailDto) {
        /**
         * @Author: xingyuchen
         * @Discription: 发送邮件
         * @param
         * @Date: 2021/7/14 10:13 下午
         */

        //调用redisActionUtil里的发送邮件和写入redis缓存的方法
        boolean b = redisActionUtil.setEmailCodeToRedis(emailDto.getUserEmail(), Constant.REGISTER_CODE, Constant.VERIFY_CODE, Constant.VERIFY_MSG);
        //如果全部成功无异常情况会返回一个true
        if (b) {
            //将成功200信息返回客户端
            return Result.succ("发送成功,请完成您的注册信息");
        } else {
            //将失败400信息返回客户端
            return Result.fail("发送失败，请确认您的邮箱");
        }
    }

    @PostMapping("/register")
    public Result register(@Validated @RequestBody LoginDto loginDto) {
        /**
         * @Author: xingyuchen
         * @Discription: 用户注册
         * @param loginDto
         * @Date: 2021/7/16 8:57 下午
         */
        //要对注册的用户进行账号是否注册过的验证
        //System.out.println(loginDto);
        int u_email = usersService.count(new QueryWrapper<Users>().eq("u_email", loginDto.getUEmail()));
        if (u_email > 0) {
            return Result.fail("邮箱用户已存在，请前往找回");
        }
        //如果用户状态码不正确，说明用户的验证码过期了或者没有发送验证码
        //状态码存在redis缓存里放3分钟，不然就在3分钟后登陆或者没有发送
        boolean emailCodeFromRedis = redisActionUtil.getEmailCodeFromRedis(loginDto.getUEmail(), loginDto.getCode(), Constant.REGISTER_CODE);
        //如果在3分钟内操作，并且实现了验证码校验，就插入该用户供以登陆
        if (emailCodeFromRedis) {
            //把信息存到Users对象中
            Users users = new Users();
            users.setUEmail(loginDto.getUEmail());
            users.setUPassword(loginDto.getUPassword());
            //执行用户插入
            boolean isSuccessRegister = usersService.saveOrUpdate(users);
            if (isSuccessRegister) {
                //成功就返回200
                return Result.succ("用户添加成功");
            } else {
                //失败就返回400
                return Result.fail("用户添加失败");
            }
        } else {
            //验证码校验失败，返回400
            return Result.fail("验证码信息不正确，请重新验证");
        }
    }

    @PostMapping("/email/sent/password")
    public Result sentEmailPassword(@Validated @RequestBody EmailDto emailDto) {
        /**
         * @Author: xingyuchen
         * @Discription: 用户密码找回——使用邮箱找回
         * @param emailDto
         * @Date: 2021/7/16 10:49 下午
         */
        //调用redisActionUtil里的发送邮件和写入redis缓存的方法
        boolean b = redisActionUtil.setEmailCodeToRedis(emailDto.getUserEmail(), Constant.FIND_PASSWORD_CODE, Constant.VERIFY_CODE, Constant.VERIFY_MSG);
        if (b) {
            //将成功200信息返回客户端
            return Result.succ("发送成功,请完成信息校验");
        } else {
            //将失败400信息返回客户端
            return Result.fail("发送失败，请确认您的邮箱");
        }
    }

    @PostMapping("/email/find/password")
    public Result findEmailPassword(@Validated @RequestBody EmailDto emailDto) throws EmailException {
        /**
         * @Author: xingyuchen
         * @Discription: 返回给用户一个验证后的密码
         * @param loginDto
         * @Date: 2021/7/16 11:12 下午
         */
        //判断校验码和缓存里的是不是一样
        boolean emailCodeFromRedis = redisActionUtil.getEmailCodeFromRedis(emailDto.getUserEmail(), emailDto.getCode(), Constant.FIND_PASSWORD_CODE);
        //如果一样的话就去查询是不是只有单用户/即用户存在/绕过检测注入进来
        if (emailCodeFromRedis) {
            //查询该邮箱的用户的个数是不是只有一个
            int isExist = usersService.count(new QueryWrapper<Users>().eq("u_email",emailDto.getUserEmail()));
            //如果等于1的话说明这个用户数据没有异常，可以得到邮件返回密码
            if (isExist == 1) {
                //得到该用户的信息，用于拿他的密码
                Users uEmail = usersService.getOne(new QueryWrapper<Users>().eq("u_email", emailDto.getUserEmail()));
                //发送邮件
                EmailUtil.sendEmail(emailDto.getUserEmail(), uEmail.getUPassword(), Constant.FIND_PASSWORD, Constant.FIND_PASSWORD_MSG);
                //将成功200信息返回客户端
                return Result.succ("信息发送成功，请前往邮箱查收您的密码");
            } else {
                //将失败400信息返回客户端
                return Result.succ("信息发送失败，邮箱数据异常，请联系工作人员稍后再试");
            }
        } else {
            //将失败400信息返回客户端
            return Result.fail("校验失败，请确认您的验证码");
        }
    }
    @PostMapping("/email/update/password")
    public Result updateEmailPassword(@Validated @RequestBody EmailDto emailDto) throws EmailException {
        /**
         * @Author: xingyuchen
         * @Discription: 返回给用户一个验证后的密码
         * @param loginDto
         * @Date: 2021/7/16 11:12 下午
         */
        //判断校验码和缓存里的是不是一样
        boolean emailCodeFromRedis = redisActionUtil.getEmailCodeFromRedis(emailDto.getUserEmail(), emailDto.getCode(), Constant.FIND_PASSWORD_CODE);
        //如果一样的话就去查询是不是只有单用户/即用户存在/绕过检测注入进来
        if (emailCodeFromRedis) {
            //查询该邮箱的用户的个数是不是只有一个
            int isExist = usersService.count(new QueryWrapper<Users>().eq("u_email",emailDto.getUserEmail()));
            //如果等于1的话说明这个用户数据没有异常，可以得到邮件返回密码
            if (isExist == 1) {
                //得到该用户的信息，用于拿他的密码
                Users uEmail = usersService.getOne(new QueryWrapper<Users>().eq("u_email", emailDto.getUserEmail()));
                //发送邮件
                EmailUtil.sendEmail(emailDto.getUserEmail(), uEmail.getUPassword(), Constant.FIND_PASSWORD, Constant.FIND_PASSWORD_MSG);
                //将成功200信息返回客户端
                return Result.succ("信息发送成功，请前往邮箱查收您的密码");
            } else {
                //将失败400信息返回客户端
                return Result.succ("信息发送失败，邮箱数据异常，请联系工作人员稍后再试");
            }
        } else {
            //将失败400信息返回客户端
            return Result.fail("校验失败，请确认您的验证码");
        }
    }
    /**
     * @Author: xingyuchen
     * @Discription: 下面的方法用于用户管理使用
     * @Methods：
     * @Date: 2021/7/17 12:08 上午
    */

    @GetMapping("/list")
    public Result list(){
        List<Roles> list = rolesService.list();
        if(list.size()>0){
            return Result.succ(list);
        }
        return Result.fail("失败");
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
            //IPage<Users> page1 = usersService.page(pages, new QueryWrapper<Users>().like("u_username",name));
            final Page page1 = usersService.listAllUserWithSearch(pages, name);
            return Result.succ(page1);
        }
        Page pages = new Page(page, Constant.PAGE_RECORD);
        //再在数据库里根据id正序排列下来
        //IPage pageUsers = usersService.page(pages, new QueryWrapper<Users>().orderByAsc("u_id").eq("deleted",0));
        Page<UserDto> pageUsers = usersService.listAllUser(pages);
        return Result.succ(pageUsers);
    }

    @GetMapping("/list/with/search/deleted")
    public Result listWithSearchDeleted(@RequestParam(value = "page") Integer page,@RequestParam(value = "name") String name){
        /**
         * @Author: xingyuchen
         * @Discription: 逻辑删除的用户查询分页或者模糊查询分页
         * @param page
         * @param name
         * @Date: 2021/8/14 7:22 下午
         */
        if ( !name.equals("") && !name.equals(null) ){
            Page pages = new Page(page, Constant.PAGE_RECORD);
            final Page page1 = usersService.listAllUserWithSearchDeleted(pages, name);
            return Result.succ(page1);
        }
        Page pages = new Page(page, Constant.PAGE_RECORD);
        //再在数据库里根据id正序排列下来
        Page<UserDto> pageUsers = usersService.listAllUserDeleted(pages);
        return Result.succ(pageUsers);
    }

    @GetMapping("/{uId}/get")
    public Result getUserById(@PathVariable(name = "uId")Integer uId){
        /**
         * @Author: xingyuchen
         * @Discription: 根据ID查该用户的所有信息，动态sql联表
         * @param uId 用户的ID自增唯一，用于查询
         * @Date: 2021/7/17 12:33 上午
        */
        // 根据Id获取用户Dto的信息
        UserDto user = usersService.getUserById(uId);
        Integer rId = user.getURoleId();
        // 如果拿到ID可靠，就会查询出来
        if(user!=null){
            List<RoleToPermissionDto> roleToPermissionById = roleToPermissionService.getRoleToPermissionById(rId);
            user.setRoleToPermissionDto(roleToPermissionById);
            //将成功200信息返回客户端
            return Result.succ(user);
        }
        //将失败400信息返回客户端
        return Result.fail("用户查询失败");

    }

    @GetMapping("/{uId}/recover")
    public Result recoverUser(@PathVariable(name = "uId")Integer uId){
        /**
         * @Author: xingyuchen
         * @Discription: 恢复逻辑删除的用户
         * @param uId
         * @Date: 2021/8/16 2:49 上午
        */
        Integer integer = usersService.recoverUser(uId);
        if(integer>0){
            return Result.succ("恢复成功");
        }
        return Result.fail("恢复失败");
    }

    @GetMapping("/{uId}/clear")
    public Result clearUser(@PathVariable(name = "uId")Integer uId){
        /**
         * @Author: xingyuchen
         * @Discription: 彻底删除的用户
         * @param uId
         * @Date: 2021/8/16 2:49 上午
         */
        Integer integer = usersService.clearUserById(uId);
        if(integer>0){
            return Result.succ("删除成功");
        }
        return Result.fail("删除失败");
    }

    @PostMapping("/update")
    public Result updateUserById(@RequestBody UserDto userDto){
        /**
         * @Author: xingyuchen
         * @Discription: 根据用户ID修改信息
         * @param uId 用户的ID自增唯一
         * @param users 用户的详细信息
         * @Date: 2021/7/17 11:02 下午
        */
        System.out.println(userDto);
        //用户修改
        Integer i = usersService.updateUserById(userDto);
        //如果修改成功
        if(i > 0) {
            //将成功200信息返回客户端
            return Result.succ("编辑成功");
        }
        //将失败400信息返回客户端
        return Result.fail("编辑失败");
    }

    @GetMapping("/{uId}/remove")
    public Result removeUserById(@PathVariable(name = "uId")Long uId){
        /**
         * @Author: xingyuchen
         * @Discription: 根据Id删除某用户
         * @param uId
         * @Date: 2021/7/18 1:23 下午
        */
        // 根据Id删除用户信息
        boolean b = usersService.removeById(uId);
        //如果修改成功
        if(b){
            //将成功200信息返回客户端
            return Result.succ("删除成功");
        }
        //将失败400信息返回客户端
        return Result.fail("删除失败");
    }

    @GetMapping("/getNav")
    public Result getCurrentUserNav(){
        /**
         * @Author: xingyuchen
         * @Discription: 动态菜单栏
         * @param
         * @Date: 2021/8/4 6:15 下午
        */
        List list = MenuUtil.menuList();
        return Result.succ(list);
    }

    @PostMapping("/save")
    public Result saveUser(@RequestBody Users users){
        boolean save = usersService.save(users);
        if(save){
            return Result.succ("新增成功");
        }
        return Result.fail("新增失败");

    }

}

