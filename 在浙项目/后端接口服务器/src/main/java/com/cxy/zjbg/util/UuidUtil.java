package com.cxy.zjbg.util;

import java.util.UUID;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description: UUID工具类
 * @Data:Created in 2021/7/16 9:27 下午
 */
public class UuidUtil {
    public static String getUuidUtil(){
        /**
         * @Author: xingyuchen
         * @Discription: 生成一个UUID
         * @param
         * @Date: 2021/7/16 9:31 下午
        */
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
