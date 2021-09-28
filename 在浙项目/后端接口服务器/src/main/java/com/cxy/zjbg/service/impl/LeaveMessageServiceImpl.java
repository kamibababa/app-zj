package com.cxy.zjbg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.entity.LeaveMessage;
import com.cxy.zjbg.mapper.LeaveMessageMapper;
import com.cxy.zjbg.service.LeaveMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 留言表 服务实现类
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@Service
public class LeaveMessageServiceImpl extends ServiceImpl<LeaveMessageMapper, LeaveMessage> implements LeaveMessageService {

    @Autowired
    private LeaveMessageMapper leaveMessageMapper;

    @Override
    public List<LeaveMessage> listAllMessageById(Integer aId) {
        return leaveMessageMapper.listAllMessageById(aId);
    }

    @Override
    public List<LeaveMessage> getAllMessageByUserId(Integer uId, Integer aId) {
        return leaveMessageMapper.getAllMessageByUserId(uId,aId);
    }

    @Override
    public List<LeaveMessage> getMessageByUserId(Integer uId) {
        return leaveMessageMapper.getMessageByUserId(uId);
    }

    @Override
    public Page<LeaveMessage> listAllMessageWithSearch(Page<LeaveMessage> page, String name) {
        return leaveMessageMapper.listAllMessageWithSearch(page,name);
    }

    @Override
    public Page<LeaveMessage> listAllMessage(Page<LeaveMessage> page) {
        return leaveMessageMapper.listAllMessage(page);
    }
}
