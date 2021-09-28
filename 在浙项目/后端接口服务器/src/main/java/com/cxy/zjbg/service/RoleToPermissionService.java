package com.cxy.zjbg.service;

import com.cxy.zjbg.common.dto.RoleToPermissionDto;
import com.cxy.zjbg.entity.RoleToPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户权限表 服务类
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
public interface RoleToPermissionService extends IService<RoleToPermission> {
    /**
     * @Author: xingyuchen
     * @Discription: 查询所有的角色权限
     * @Date: 2021/7/19 1:01 下午
     */
    public List<RoleToPermissionDto> listRoleToPermission();

    /**
     * @Author: xingyuchen
     * @Discription: 根据传入的id获取单一的数据传输对象
     * @param rId id
     * @Date: 2021/7/19 4:42 下午
     */
    public List<RoleToPermissionDto> getRoleToPermissionById(Integer rId);
}
