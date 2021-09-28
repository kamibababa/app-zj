package com.cxy.zjbg.common.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description:
 * @Data:Created in 2021/7/21 10:42 上午
 */
@Data
public class CharacterDto implements Serializable {

    @JsonProperty(value = "cId")
    private Integer cId;

    @JsonProperty(value = "chName")
    private String chName;

    @JsonProperty(value = "cCityId")
    private Integer cCityId;

    @JsonProperty(value = "cDeedsSynopsis")
    private String cDeedsSynopsis;

    @JsonProperty(value = "cDeedsDetail")
    private String cDeedsDetail;

    @ApiModelProperty(value = "人物评价")
    private String cEvaluate;

    @ApiModelProperty(value = "人物成就")
    private String cAchievement;

    @JsonProperty(value = "cImg")
    private String cImg;

    @JsonProperty(value = "gmtCreate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @JsonProperty(value = "gmtModified")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @JsonProperty(value = "cName")
    private String cName;
}
