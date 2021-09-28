package com.cxy.zjbg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cxy.zjbg.entity.RedBase;
import com.cxy.zjbg.entity.Roles;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description:
 * @Data:Created in 2021/8/20 2:25 上午
 */
public interface RedBaseService extends IService<RedBase> {
    /**
     * @Author: xingyuchen
     * @Discription: 模糊查询所有基地信息
     * @param null
     * @Date: 2021/8/15 9:47 下午
     */
    Page<RedBase> listAllRedBaseWithSearch(Page<RedBase> page, @Param("name") String name);

    /**
     * @Author: xingyuchen
     * @Discription: 列出所有的基地详细信息
     * @Date: 2021/8/4 1:01 下午
     */
    Page<RedBase> listAllRedBase(Page<RedBase> page);
}
