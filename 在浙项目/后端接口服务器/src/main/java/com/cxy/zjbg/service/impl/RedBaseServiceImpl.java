package com.cxy.zjbg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxy.zjbg.entity.Article;
import com.cxy.zjbg.entity.RedBase;
import com.cxy.zjbg.mapper.ArticleMapper;
import com.cxy.zjbg.mapper.RedBaseMapper;
import com.cxy.zjbg.service.ArticleService;
import com.cxy.zjbg.service.RedBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description:
 * @Data:Created in 2021/8/20 2:26 上午
 */
@Service
public class RedBaseServiceImpl  extends ServiceImpl<RedBaseMapper, RedBase> implements RedBaseService {

    @Autowired
    private RedBaseMapper redBaseMapper;

    @Override
    public Page<RedBase> listAllRedBaseWithSearch(Page<RedBase> page, String name) {
        return redBaseMapper.listAllRedBaseWithSearch(page,name);
    }

    @Override
    public Page<RedBase> listAllRedBase(Page<RedBase> page) {
        return redBaseMapper.listAllRedBase(page);
    }
}
