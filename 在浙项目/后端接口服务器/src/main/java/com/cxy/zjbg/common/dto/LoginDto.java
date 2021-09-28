package com.cxy.zjbg.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {
    private int uId;

    @NotBlank(message = "邮箱不能为空")
    @JsonProperty(value = "uEmail")
    private String uEmail;

    @NotBlank(message = "密码不能为空")
    @JsonProperty(value = "uPassword")
    private String uPassword;

    @JsonProperty(value = "code")
    private String code;

}
