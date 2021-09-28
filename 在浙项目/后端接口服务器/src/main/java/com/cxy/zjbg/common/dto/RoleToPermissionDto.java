package com.cxy.zjbg.common.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.cxy.zjbg.entity.Permission;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description:
 * @Data:Created in 2021/7/19 12:43 下午
 */
@Data
public class RoleToPermissionDto implements Serializable {
    @JsonProperty(value = "rpId")
    private Integer rpId;

    @JsonProperty(value = "rId")
    private Integer rId;

    @JsonProperty(value = "rName")
    private String rName;

    @JsonProperty(value = "pId")
    private Integer pId;

    @JsonProperty(value = "pName")
    private String pName;

    @JsonProperty(value = "children")
    private List<?> children;

    @JsonProperty(value = "gmtCreate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @JsonProperty(value = "gmtModified")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
