package com.cxy.zjbg.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户权限表
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_role_to_permission")
@ApiModel(value="RoleToPermission对象", description="用户权限表")
public class RoleToPermission implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色权限ID(自增)")
    @TableId(value = "rp_id", type = IdType.AUTO)
    private Integer rpId;

    @ApiModelProperty(value = "角色ID")
    private Integer rId;

    @ApiModelProperty(value = "权限ID")
    private Integer pId;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
