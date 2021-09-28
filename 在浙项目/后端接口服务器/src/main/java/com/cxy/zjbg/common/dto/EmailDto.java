package com.cxy.zjbg.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description:
 * @Data:Created in 2021/7/14 10:22 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto implements Serializable {
    @NotBlank(message = "邮箱不能为空")
    @JsonProperty(value = "userEmail")
    private String userEmail;

    @JsonProperty(value = "code")
    private String code;
}
