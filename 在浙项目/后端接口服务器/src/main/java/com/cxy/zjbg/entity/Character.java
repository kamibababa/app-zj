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
 * 红色人物表
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_character")
@ApiModel(value="Character对象", description="红色人物表")
public class Character implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "红色人物ID(自增)")
    @TableId(value = "c_id", type = IdType.AUTO)
    private Integer cId;

    @ApiModelProperty(value = "人物名")
    private String chName;

    @ApiModelProperty(value = "城市ID")
    private Integer cCityId;

    @ApiModelProperty(value = "人物简介")
    private String cDeedsSynopsis;

    @ApiModelProperty(value = "人物生平")
    private String cDeedsDetail;

    @ApiModelProperty(value = "人物评价")
    private String cEvaluate;

    @ApiModelProperty(value = "人物成就")
    private String cAchievement;

    @ApiModelProperty(value = "人物形象图")
    private String cImg;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
