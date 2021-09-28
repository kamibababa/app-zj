package com.cxy.zjbg.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description:
 * @Data:Created in 2021/8/20 2:20 上午
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_red_base")
@ApiModel(value="RedBase对象", description="红色基地表")
public class RedBase {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "红色基地ID")
    @JsonProperty(value = "rId")
    @TableId(value = "r_id", type = IdType.AUTO)
    private Integer rId;

    @ApiModelProperty(value = "基地名")
    @JsonProperty(value = "rName")
    private String rName;

    @ApiModelProperty(value = "基地展示图")
    @JsonProperty(value = "rImg")
    private String rImg;

    @ApiModelProperty(value = "基地简介")
    @JsonProperty(value = "rSynopsis")
    private String rSynopsis;

    @ApiModelProperty(value = "基地详情")
    @JsonProperty(value = "rDetail")
    private String rDetail;

    @ApiModelProperty(value = "成就")
    @JsonProperty(value = "rAchievement")
    private String rAchievement;

    @ApiModelProperty(value = "地理位置")
    @JsonProperty(value = "rAddress")
    private String rAddress;

    @ApiModelProperty(value = "城市ID")
    @JsonProperty(value = "rCityId")
    private Integer rCityId;

    @JsonProperty(value = "cName")
    @TableField(exist = false)
    private String cName;

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
}
