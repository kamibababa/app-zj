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
 * 文章表
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_article")
@ApiModel(value="Article对象", description="文章表")
public class Article implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "文章ID(自增)")
    @TableId(value = "a_id", type = IdType.AUTO)
    @JsonProperty(value = "aId")
    private Integer aId;

    @ApiModelProperty(value = "50字以内的文章标题")
    @JsonProperty(value = "aTitle")
    private String aTitle;

    @ApiModelProperty(value = "市ID")
    @JsonProperty(value = "aCityId")
    private Integer aCityId;

    @ApiModelProperty(value = "封面图片")
    @JsonProperty(value = "aCoverImg")
    private String aCoverImg;

    @ApiModelProperty(value = "作者用户名")
    @JsonProperty(value = "aAuthor")
    private String aAuthor;

    @ApiModelProperty(value = "文章内容")
    @JsonProperty(value = "aContent")
    private String aContent;

    @ApiModelProperty(value = "文章内容")
    @JsonProperty(value = "aContentTxt")
    private String aContentTxt;

    @ApiModelProperty(value = "审核状态")
    @JsonProperty(value = "aStatus")
    private Integer aStatus;

    @ApiModelProperty(value = "类型：1城市2基地3烈士4是看点")
    @JsonProperty(value = "aType")
    private Integer aType;

    @ApiModelProperty(value = "逻辑删除")
    @JsonProperty(value = "deleted")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @JsonProperty(value = "gmtCreate")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonProperty(value = "gmtModified")
    private Date gmtModified;


}
