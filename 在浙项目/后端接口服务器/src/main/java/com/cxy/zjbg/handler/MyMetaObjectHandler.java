package com.cxy.zjbg.handler;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description:
 * @Data:Created in 2021/7/19 9:18 下午
 */

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    // 插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill.....");
         this.setFieldValByName("gmtCreate",new Date(),metaObject);
         this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
    // 更新时的填充策略
     @Override
     public void updateFill(MetaObject metaObject) {
        log.info("start update fill.....");
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}