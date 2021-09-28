package com.cxy.zjbg.util;

import com.cxy.zjbg.common.constant.Constant;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

import java.util.Random;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description:
 * @Data:Created in 2021/7/14 6:01 下午
 */
public class EmailUtil {
    //  设置随机数
    private static Random random = new Random();

    //  获取4位随机数
    public static String getRandomString(){
        String str="";
        for (int i = 0; i < 6; i++) {
            str+=random.nextInt(10);
        }
        return str;
    }

    public static void sendEmail(String userEmail, String code,String theme,String msg) throws EmailException {
        HtmlEmail email=new HtmlEmail();//创建一个HtmlEmail实例对象
        email.setHostName("smtp.163.com");//邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setCharset("utf-8");//设置发送的字符类型
        email.addTo(userEmail);//设置收件人
        email.setFrom(Constant.OWN_EMAIL,"zj-App");//发送人的邮箱为自己的，用户名可以随便填
        email.setAuthentication(Constant.OWN_EMAIL,"WRQOPHBMZWJTVGJG");//设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
        email.setSubject(Constant.APP_NAME+"APP-"+theme);//设置发送主题
        email.setMsg("<p style='font-size:15px;font-family:'微软雅正''><strong>【"+ Constant.APP_NAME +"】</strong>欢迎使用我们的APP，您的"+theme+":<br><span style='font-size:25px'>"+code+"</span>"+msg+"</p>");//设置发送内容
        email.send();//进行发送
    }
}
