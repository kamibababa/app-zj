package com.cxy.zjbg;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.constant.Constant;
import com.cxy.zjbg.common.dto.UserDto;
import com.cxy.zjbg.entity.Users;
import com.cxy.zjbg.service.UsersService;
import org.apache.commons.mail.EmailException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest

class ZjBgApplicationTests {

    @Autowired
    UsersService usersService;

    @Test
    void contextLoads(){
        Page<UserDto> userDtoPage = new Page<>(1, 10);
        Page<UserDto> userDtoPage1 = usersService.listAllUser(userDtoPage);
        int total = (int)userDtoPage1.getTotal();
        System.out.println(userDtoPage1.getRecords());
    }

}
