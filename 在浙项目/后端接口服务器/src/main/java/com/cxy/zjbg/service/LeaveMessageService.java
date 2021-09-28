package com.cxy.zjbg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.entity.LeaveMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 留言表 服务类
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
public interface LeaveMessageService extends IService<LeaveMessage> {
    /**
     * @Author: xingyuchen
     * @Discription: 根据ID获取留言信息
     * @Date: 2021/7/20 9:58 下午
     */
    public List<LeaveMessage> listAllMessageById(Integer aId);

    /**
     * @Author: xingyuchen
     * @Discription: 查询某篇文章下该用户的所有留言
     * @Date: 2021/7/20 10:19 下午
     */
    public List<LeaveMessage> getAllMessageByUserId( Integer uId,Integer aId);

    /**
     * @Author: xingyuchen
     * @Discription: 查询某一用户的所有留言信息
     * @Date: 2021/7/20 11:24 下午
     */
    public List<LeaveMessage> getMessageByUserId(@Param("uId") Integer uId);


    /**
     * @Author: xingyuchen
     * @Discription: 模糊查询所有留言信息
     * @param null
     * @Date: 2021/8/15 9:47 下午
     */
    Page<LeaveMessage> listAllMessageWithSearch(Page<LeaveMessage> page, @Param("name") String name);

    /**
     * @Author: xingyuchen
     * @Discription: 列出所有的留言信息
     * @Date: 2021/8/4 1:01 下午
     */
    Page<LeaveMessage> listAllMessage(Page<LeaveMessage> page);
}
