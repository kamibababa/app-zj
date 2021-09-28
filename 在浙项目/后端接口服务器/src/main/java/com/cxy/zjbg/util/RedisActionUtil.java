package com.cxy.zjbg.util;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.constant.Constant;
import com.cxy.zjbg.common.dto.ArticleDto;
import com.cxy.zjbg.common.lang.Result;
import com.cxy.zjbg.entity.City;
import com.cxy.zjbg.entity.LeaveMessage;
import com.cxy.zjbg.service.ArticleService;
import com.cxy.zjbg.service.CityService;
import com.cxy.zjbg.service.LeaveMessageService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description:
 * @Data:Created in 2021/7/14 9:24 下午
 */

@Component
public class RedisActionUtil{

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CityService cityService;

    @Resource
    private ArticleService articleService;

    @Resource
    private LeaveMessageService leaveMessageService;

    public boolean setEmailCodeToRedis(String userEmail,String statusCode,String theme,String msg) {
        /**
         * @Author: xingyuchen
         * @Discription: 发送邮件/将验证码写入redis
         * @param userEmail
         * @Date: 2021/7/16 9:41 下午
        */
        Assert.notNull(userEmail, "邮箱信息为空");
        String code = EmailUtil.getRandomString();
        try {
            EmailUtil.sendEmail(userEmail,code,theme,msg);
        } catch (Exception e) {
            Result.fail("邮件发送失败");
        }
        if (userEmail.equals("")){
            return false;
        }else{
            redisTemplate.opsForValue().set(statusCode+userEmail,code, Constant.EMAIL_EXIT_TIME, TimeUnit.SECONDS);
            return true;
        }
    }

    public boolean getEmailCodeFromRedis(String userEmail,String code,String statusCode){
        /**
         * @Author: xingyuchen
         * @Discription: 获取redis中是否已经存在这个用户/不存在会以异常方式抛出
         * @param userEmail
         * @Date: 2021/7/16 9:44 下午
        */
        Object o = redisTemplate.opsForValue().get(statusCode + userEmail);
        System.out.println(statusCode + userEmail);
        System.out.println(o);
        Assert.notNull(o, "您的验证信息已过期，请重新发送验证码");
        if(!o.equals(code)){
            return false;
        }else{
            return true;
        }
    }

    public Long saveArticleClickStarRedis(String aId,String uId) {
        /**
         * @Author: xingyuchen
         * @Discription: 文章点赞
         * @param aId
         * @param uId
         * @Date: 2021/8/19 1:45 上午
        */
        redisTemplate.opsForSet().add("Article"+aId+"User",uId);
        Long size = redisTemplate.opsForSet().size("Article" + aId + "User");
        return size;
    }

    public Long saveArticleClickStarWithoutClickRedis(String aId){
        Long size = redisTemplate.opsForSet().size("Article" + aId + "User");
        return size;
    }

    public Long removeArticleClickStarRedis(String aId,String uId) {
        /**
         * @Author: xingyuchen
         * @Discription: 文章取消赞
         * @param aId
         * @param uId
         * @Date: 2021/8/19 1:45 上午
        */
        redisTemplate.opsForSet().remove("Article"+aId+"User",uId);
        Long size = redisTemplate.opsForSet().size("Article" + aId + "User");
        return size;
    }

    public Long lookArticleStarRedis(String aId,String uId) {
        /**
         * @Author: xingyuchen
         * @Discription: 每篇文章的浏览量
         * @param aId
         * @param uId
         * @Date: 2021/8/19 1:46 上午
        */
        redisTemplate.opsForHyperLogLog().add("Article"+aId+"All",uId);
        Long size = redisTemplate.opsForHyperLogLog().size("Article" + aId + "All");
        return size;
    }



    public Long lookArticleStarWithoutClick(String aId){
        Long size = redisTemplate.opsForHyperLogLog().size("Article" + aId + "All");
        return size;
    }

    public Page<ArticleDto> getAllMessageRedis(Integer page,String name){
        /**
         * @Author: xingyuchen
         * @Discription: 获得每篇文章的点赞和浏览量
         * @param
         * @Date: 2021/8/20 6:25 上午
        */
        Page pages = new Page(page, Constant.PAGE_RECORD);
        Page<ArticleDto> basePage = articleService.listAllArticleWithSearch(pages,name);
        for (ArticleDto articleDto:basePage.getRecords()) {
            Long size = redisTemplate.opsForSet().size("Article" + articleDto.getAId() + "User");
            articleDto.setStar(size);
            Long size1 = redisTemplate.opsForHyperLogLog().size("Article" + articleDto.getAId() + "All");
            articleDto.setLook(size1);
        }
        return basePage;

    }

    public Long saveMessageClickStarRedis(String mId,String uId) {
        /**
         * @Author: xingyuchen
         * @Discription: 评论点赞
         * @param mId
         * @param uId
         * @Date: 2021/8/19 1:48 上午
        */
        redisTemplate.opsForSet().add("Message"+mId+"User",uId);
        Long size = redisTemplate.opsForSet().size("Message" + mId + "User");
        return size;
    }

    public Long saveMessageClickStarWithoutClickRedis(String mId) {
        Long size = redisTemplate.opsForSet().size("Message" + mId + "User");
        return size;
    }

    public Long removeMessageClickStarRedis(String mId,String uId) {
        /**
         * @Author: xingyuchen
         * @Discription: 评论取消赞
         * @param mId
         * @param uId
         * @Date: 2021/8/19 1:48 上午
        */
        redisTemplate.opsForSet().remove("Article"+mId+"User",uId);
        Long size = redisTemplate.opsForSet().size("Article" + mId + "User");
        return size;
    }

    public Page<LeaveMessage> listAllMessage(Integer page,String name){
        Page pages = new Page(page, Constant.PAGE_RECORD);
        Page<LeaveMessage> basePage = leaveMessageService.listAllMessageWithSearch(pages,name);
        for (LeaveMessage leaveMessage: basePage.getRecords()) {
            Long size = redisTemplate.opsForSet().size("Message" + leaveMessage.getMId() + "User");
            leaveMessage.setStar(size);
        }
        return basePage;
    }

    public Long lookCityFootByIdRedis(Integer cId,Integer uId){
        /**
         * @Author: xingyuchen
         * @Discription: 每座城市的浏览足迹加
         * @param cId
         * @param uId
         * @Date: 2021/8/19 1:50 上午
        */
        Long add = redisTemplate.opsForHyperLogLog().add("City" + cId + "Foot", uId);
        return add;
    }

    public List lookAllCityFootRedis(){
        /**
         * @Author: xingyuchen
         * @Discription: 所有城市的浏览足迹
         * @param
         * @Date: 2021/8/19 2:16 上午
        */
        Set<String> keys = redisTemplate.keys("City*");
        List<City> list=new ArrayList<>();
        for (String key : keys) {

            //System.out.println(key);
            int front = key.indexOf("y");
            int after = key.indexOf("F");
            String i = key.substring(front + 1, after);
            System.out.println(Integer.valueOf(i));
            City city = cityService.getById(Integer.valueOf(i));
            // 获取key对应值
            Long size = redisTemplate.opsForHyperLogLog().size(key);
            city.setCityFoot(size-1);
            list.add(city);
        }
        return list;
    }

    public void removeCityFootRedis(){
        /**
         * @Author: xingyuchen
         * @Discription: 清空所有城市足迹
         * @param
         * @Date: 2021/8/19 2:20 下午
        */
        Set<String> keys = redisTemplate.keys("City*");
        for (String key : keys) {
            // 获取key对应值
            redisTemplate.opsForHyperLogLog().delete(key);
            //System.out.println("删除");
        }
    }

}
