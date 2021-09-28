package com.cxy.zjbg.common.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.cxy.zjbg.entity.Permission;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description:
 * @Data:Created in 2021/7/17 8:59 上午
 */
@Data
public class UserDto implements Serializable {

    @JsonProperty(value = "uId")
    private Long uId;

    @JsonProperty(value = "uUsername")
    private String uUsername;

    @JsonProperty(value = "uEmail")
    private String uEmail;

    @JsonProperty(value = "uPassword")
    private String uPassword;

    @JsonProperty(value = "uSex")
    private Integer uSex;

    @JsonProperty(value = "uAge")
    private Integer uAge;

    @JsonProperty(value = "uTelephone")
    private String uTelephone;

    @JsonProperty(value = "uRoleId")
    private Integer uRoleId;

    @JsonProperty(value = "rName")
    private String rName;

    @JsonProperty(value = "permission")
    private List<RoleToPermissionDto> roleToPermissionDto;

    @JsonProperty(value = "deleted")
    private Integer deleted;

    @JsonProperty(value = "gmtCreate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @JsonProperty(value = "gmtModified")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @JsonProperty(value = "status")
    private Integer status;
}
