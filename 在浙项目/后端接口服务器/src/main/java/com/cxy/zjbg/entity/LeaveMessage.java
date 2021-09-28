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
 * 留言表
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_leave_message")
@ApiModel(value="LeaveMessage对象", description="留言表")
public class LeaveMessage implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "留言id")
    @JsonProperty(value = "mId")
    @TableId(value = "m_id", type = IdType.AUTO)
    private Integer mId;

    @ApiModelProperty(value = "留言人ID")
    @JsonProperty(value = "mUserId")
    private Long mUserId;

    @TableField(exist = false)
    @JsonProperty(value = "uName")
    private String uName;

    @ApiModelProperty(value = "200字内留言")
    @JsonProperty(value = "mContent")
    private String mContent;

    @ApiModelProperty(value = "文章ID")
    @JsonProperty(value = "mArticleId")
    private Integer mArticleId;

    @JsonProperty(value = "aTitle")
    @TableField(exist = false)
    private String aTitle;

    @JsonProperty(value = "star")
    @TableField(exist = false)
    private Long star;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
