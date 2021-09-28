package com.cxy.zjbg.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 市级表
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_city")
@ApiModel(value="City对象", description="市级表")
public class City implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "市ID(自增)")
    @TableId(value = "c_id", type = IdType.AUTO)
    private Integer cId;

    @ApiModelProperty(value = "市名称")
    private String cName;

    @ApiModelProperty(value = "城市简介")
    private String cHeaderIntroduce;

    @ApiModelProperty(value = "地理环境")
    private String cIntroduceDetail;

    @ApiModelProperty(value = "市自然资源")
    private String cNature;

    @ApiModelProperty(value = "市风景名胜")
    private String cSights;

    @ApiModelProperty(value = "百年历程")
    private String cHistory;

    @ApiModelProperty(value = "视频")
    private String cVideo;

    @ApiModelProperty(value = "市形状图案")
    private String cShapeImg;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @JsonProperty(value = "cityFoot")
    @TableField(exist = false)
    private Long cityFoot;

}
