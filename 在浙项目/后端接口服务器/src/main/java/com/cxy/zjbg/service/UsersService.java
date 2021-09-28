package com.cxy.zjbg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.dto.UserDto;
import com.cxy.zjbg.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
public interface UsersService extends IService<Users> {
    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查该用户的所有信息，动态sql联表
     * @param uId 用户的ID自增唯一，用于查询
     * @Date: 2021/7/17 12:36 上午
     */
    UserDto getUserById(Integer uId);

    /**
     * @Author: xingyuchen
     * @Discription:
     * @param null
     * @Date: 2021/8/11 9:17 下午
    */
    Integer updateUserById(UserDto userDto);

    /**
     * @Author: xingyuchen
     * @Discription: 列出所有的用户详细信息
     * @param null
     * @Date: 2021/8/4 1:01 下午
     */
    Page<UserDto> listAllUser(Page<UserDto> page);

    /**
     * @Author: xingyuchen
     * @Discription: 模糊查询所有用户信息
     * @param null
     * @Date: 2021/8/15 9:47 下午
     */
    Page<UserDto> listAllUserWithSearch(Page<UserDto> page,String name);

    /**
     * @Author: xingyuchen
     * @Discription: 列出逻辑删除的所有的用户详细信息
     * @Date: 2021/8/4 1:01 下午
     */
    Page<UserDto> listAllUserDeleted(Page<UserDto> page);

    /**
     * @Author: xingyuchen
     * @Discription: 逻辑删除的模糊查询所有用户信息
     * @param null
     * @Date: 2021/8/15 9:47 下午
     */
    Page<UserDto> listAllUserWithSearchDeleted(Page<UserDto> page, String name);

    /**
     * @Author: xingyuchen
     * @Discription: 恢复的用户就将deleted值变成0
     * @param null
     * @Date: 2021/8/16 2:46 上午
     */
    Integer recoverUser(Integer uId);

    /**
     * @Author: xingyuchen
     * @Discription: 彻底删除用户
     * @param null
     * @Date: 2021/8/16 12:35 下午
     */
    Integer clearUserById(Integer uId);
}
