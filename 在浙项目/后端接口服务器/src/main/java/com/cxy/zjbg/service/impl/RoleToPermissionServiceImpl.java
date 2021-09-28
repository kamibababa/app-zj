package com.cxy.zjbg.service.impl;

import com.cxy.zjbg.common.dto.RoleToPermissionDto;
import com.cxy.zjbg.entity.RoleToPermission;
import com.cxy.zjbg.mapper.RoleToPermissionMapper;
import com.cxy.zjbg.service.RoleToPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户权限表 服务实现类
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@Service
public class RoleToPermissionServiceImpl extends ServiceImpl<RoleToPermissionMapper, RoleToPermission> implements RoleToPermissionService {

    @Autowired
    private RoleToPermissionMapper roleToPermissionMapper;

    @Override
    public List<RoleToPermissionDto> listRoleToPermission() {
        return roleToPermissionMapper.listRoleToPermission();
    }

    @Override
    public List<RoleToPermissionDto> getRoleToPermissionById(Integer rId) {
        return roleToPermissionMapper.getRoleToPermissionById(rId);
    }


}
