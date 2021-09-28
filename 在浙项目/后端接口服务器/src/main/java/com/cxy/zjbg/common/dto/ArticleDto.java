package com.cxy.zjbg.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@Data
public class ArticleDto implements Serializable {

    @JsonProperty(value = "aId")
    private Integer aId;

    @JsonProperty(value = "aTitle")
    private String aTitle;

    @JsonProperty(value = "aCityId")
    private Integer aCityId;

    @JsonProperty(value = "cName")
    private String cName;

    @JsonProperty(value = "aCoverImg")
    private String aCoverImg;

    @JsonProperty(value = "aAuthor")
    private String aAuthor;

    @JsonProperty(value = "aContent")
    private String aContent;

    @JsonProperty(value = "aContentTxt")
    private String aContentTxt;

    @JsonProperty(value = "aStatus")
    private Integer aStatus;

    @JsonProperty(value = "aType")
    private Integer aType;

    @JsonProperty(value = "deleted")
    private Integer deleted;

    @JsonProperty(value = "star")
    private Long star;

    @JsonProperty(value = "look")
    private Long look;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JsonProperty(value = "gmtCreate")
    private Date gmtCreate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JsonProperty(value = "gmtModified")
    private Date gmtModified;

}
