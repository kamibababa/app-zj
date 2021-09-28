package com.cxy.zjbg.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_users")
@ApiModel(value="Users对象", description="用户表")
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "u_id", type = IdType.AUTO)
    @JsonProperty(value = "uId")
    private Long uId;

    @ApiModelProperty(value = "用户名")
    @JsonProperty(value = "uUsername")
    private String uUsername;

    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @JsonProperty(value = "uEmail")
    private String uEmail;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    @JsonProperty(value = "uPassword")
    private String uPassword;

    @ApiModelProperty(value = "性别")
    @JsonProperty(value = "uSex")
    private Integer uSex;

    @ApiModelProperty(value = "年龄")
    @JsonProperty(value = "uAge")
    private Integer uAge;

    @ApiModelProperty(value = "电话号码")
    @JsonProperty(value = "uTelephone")
    private String uTelephone;

    @ApiModelProperty(value = "角色ID")
    @JsonProperty(value = "uRoleId")
    private Integer uRoleId;

    @ApiModelProperty(value = "逻辑删除")
    @JsonProperty(value = "deleted")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonProperty(value = "gmtCreate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonProperty(value = "gmtModified")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date gmtModified;

    @ApiModelProperty(value = "用户状态")
    @JsonProperty(value = "status")
    private Integer status;


}
