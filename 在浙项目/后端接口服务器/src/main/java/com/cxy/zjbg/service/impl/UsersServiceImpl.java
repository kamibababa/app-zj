package com.cxy.zjbg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.dto.UserDto;
import com.cxy.zjbg.entity.Users;
import com.cxy.zjbg.mapper.UsersMapper;
import com.cxy.zjbg.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;
    /**
     * @Author: xingyuchen
     * @Discription: 根据ID查该用户的所有信息，动态sql联表
     * @param uId 用户的ID自增唯一，用于查询
     * @Date: 2021/7/17 12:36 上午
     */
    @Override
    public UserDto getUserById(Integer uId) {
        return usersMapper.getUserById(uId);
    }

    /**
     * @Author: xingyuchen
     * @Discription:
     * @param userDto 用户修改的Id
     * @Date: 2021/7/17 11:08 下午
     */
    @Override
    public Integer updateUserById(UserDto userDto) {
        return usersMapper.updateUserById(userDto);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 列出所有的用户详细信息
     * @Date: 2021/8/4 1:01 下午
     */
    @Override
    public Page<UserDto> listAllUser(Page<UserDto> page) {
        return usersMapper.listAllUser(page);
    }

    @Override
    public Page<UserDto> listAllUserWithSearch(Page<UserDto> page, String name) {
        return usersMapper.listAllUserWithSearch(page,name);
    }

    @Override
    public Page<UserDto> listAllUserDeleted(Page<UserDto> page) {
        return usersMapper.listAllUserDeleted(page);
    }

    @Override
    public Page<UserDto> listAllUserWithSearchDeleted(Page<UserDto> page, String name) {
        return usersMapper.listAllUserWithSearchDeleted(page,name);
    }

    @Override
    public Integer recoverUser(Integer uId) {
        return usersMapper.recoverUser(uId);
    }

    @Override
    public Integer clearUserById(Integer uId) {
        return usersMapper.clearUserById(uId);
    }


}
