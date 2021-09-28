package com.cxy.zjbg.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.constant.Constant;
import com.cxy.zjbg.common.dto.ArticleDto;
import com.cxy.zjbg.common.lang.Result;
import com.cxy.zjbg.entity.LeaveMessage;
import com.cxy.zjbg.service.LeaveMessageService;
import com.cxy.zjbg.util.RedisActionUtil;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 留言表 前端控制器
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@RestController
@RequestMapping("/message")
public class LeaveMessageController {

    @Autowired
    private LeaveMessageService leaveMessageService;

    @Autowired
    private RedisActionUtil redisActionUtil;

    @GetMapping("/list/with/search")
    public Result listMessageWithSearch(@RequestParam(value = "page") Integer page, @RequestParam(value = "name") String name){
        /**
         * @Author: xingyuchen
         * @Discription: 分页查询所有文章或者模糊查询
         * @param page
         * @param name
         * @Date: 2021/8/20 2:51 上午
         */
        if ( !name.equals("") && !name.equals(null) ){
            Page pages = new Page(page, Constant.PAGE_RECORD);
            final Page page1 = leaveMessageService.listAllMessageWithSearch(pages, name);
            return Result.succ(page1);
        }
        Page pages = new Page(page, Constant.PAGE_RECORD);
        Page<LeaveMessage> basePage = leaveMessageService.listAllMessage(pages);
        return Result.succ(basePage);
    }

    @GetMapping("/list/star")
    public Result listMessageStar(@RequestParam(value = "page") Integer page, @RequestParam(value = "name") String name){
        /**
         * @Author: xingyuchen
         * @Discription: 获取每一个留言的点赞
         * @param page
         * @param name
         * @Date: 2021/8/20 9:06 上午
        */
        Page<LeaveMessage> listAllMessage = redisActionUtil.listAllMessage(page, name);
        return Result.succ(listAllMessage);
    }

    @GetMapping("/{aId}/list")
    public Result listAllMessage(@PathVariable(name = "aId")Integer aId){
        /**
         * @Author: xingyuchen
         * @Discription: 查询某一篇文章下的所有留言
         * @param aId
         * @Date: 2021/7/20 10:12 下午
        */
        List<LeaveMessage> leaveMessages = leaveMessageService.listAllMessageById(aId);
        if(leaveMessages.size()>0){
            return Result.succ(leaveMessages);
        }
        return Result.fail("查询条数为空");
    }

    @GetMapping("/{uId}/{aId}/get")
    public Result getMessageById(@PathVariable(name = "uId")Integer uId,@PathVariable(name = "aId")Integer aId){
        /**
         * @Author: xingyuchen
         * @Discription: 查询该作者在某篇文章下的留言
         * @param uId
         * @param aId
         * @Date: 2021/7/20 10:18 下午
        */
        List<LeaveMessage> allMessageByUserId = leaveMessageService.getAllMessageByUserId(uId, aId);
        if(allMessageByUserId.size()>0){
            return Result.succ(allMessageByUserId);
        }
        return Result.fail("查询条数为空");
    }

    @GetMapping("/{uId}/get")
    public Result getMessageByUserId(@PathVariable(name = "uId")Integer uId){
        /**
         * @Author: xingyuchen
         * @Discription: 查询该用户所有留言信息
         * @param uId
         * @Date: 2021/7/20 11:29 下午
        */
        List<LeaveMessage> messageByUserId = leaveMessageService.getMessageByUserId(uId);
        if(messageByUserId.size()>0){
            return Result.succ(messageByUserId);
        }
        return Result.fail("留言信息为空");
    }

    @GetMapping("/{mId}/remove")
    public Result removeMessageById(@PathVariable(name = "mId")Integer mId){
        /**
         * @Author: xingyuchen
         * @Discription: 删除留言信息
         * @param mId
         * @Date: 2021/7/20 11:32 下午
        */
        boolean b = leaveMessageService.removeById(mId);
        if(b){
            return Result.succ("删除成功");
        }
        return Result.fail("删除失败");
    }

    @PostMapping("/update")
    public Result updateMessageById(@RequestBody LeaveMessage leaveMessage){
        /**
         * @Author: xingyuchen
         * @Discription: 修改留言信息
         * @param mId
         * @Date: 2021/7/20 11:35 下午
        */
        boolean b = leaveMessageService.updateById(leaveMessage);
        if(b){
            return Result.succ("修改成功");
        }
        return Result.fail("修改失败");
    }

    @PostMapping("/save")
    public Result saveMessage(@RequestBody LeaveMessage leaveMessage){
        /**
         * @Author: xingyuchen
         * @Discription: 新增留言信息
         * @param leaveMessage
         * @Date: 2021/7/20 11:38 下午
        */
        boolean save = leaveMessageService.save(leaveMessage);
        if (save){
            return Result.succ("留言成功");
        }
        return Result.fail("留言失败");
    }


    @GetMapping("/{mId}/click/save")
    public Result saveMessageRedis(@PathVariable(name = "mId")String mId,String uId){
        /**
         * @Author: xingyuchen
         * @Discription: 点赞功能
         * @param aId
         * @param uId
         * @Date: 2021/7/20 9:39 下午
         */
        Long aLong = redisActionUtil.saveMessageClickStarRedis(mId, uId);
        return Result.succ(aLong);
    }

    @GetMapping("/{mId}/without/click/save")
    public Result saveMessageRedis(@PathVariable(name = "mId")String mId){
        Long aLong = redisActionUtil.saveMessageClickStarWithoutClickRedis(mId);
        return Result.succ(aLong);
    }

    @GetMapping("/{aId}/click/remove")
    public Result removeMessageRedis(@PathVariable(name = "mId")@RequestBody String mId, String uId){
        /**
         * @Author: xingyuchen
         * @Discription: 取消点赞功能
         * @param aId
         * @param uId
         * @Date: 2021/7/20 9:39 下午
         */
        Long aLong = redisActionUtil.removeMessageClickStarRedis(mId, uId);
        return Result.succ(aLong);
    }
}

