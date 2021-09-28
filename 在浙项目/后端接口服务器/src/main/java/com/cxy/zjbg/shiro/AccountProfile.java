package com.cxy.zjbg.shiro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

//非私密信息封装用于给Shiro公开
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountProfile implements Serializable {
    private Long uId;

    private String uUsername;

    private String uEmail;

    private Integer uSex;

    private Integer uAge;

    private String uTelephone;

    private Integer uRoleId;

    private Integer deleted;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer status;

}