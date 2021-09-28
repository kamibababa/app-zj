package com.cxy.zjbg.mapper;

import com.cxy.zjbg.common.dto.RoleToPermissionDto;
import com.cxy.zjbg.entity.RoleToPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户权限表 Mapper 接口
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@Mapper
public interface RoleToPermissionMapper extends BaseMapper<RoleToPermission> {
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
    public List<RoleToPermissionDto> getRoleToPermissionById(@Param("rId") Integer rId);

}
