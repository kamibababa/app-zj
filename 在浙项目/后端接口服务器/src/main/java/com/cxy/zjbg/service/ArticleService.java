package com.cxy.zjbg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.dto.ArticleDto;
import com.cxy.zjbg.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
public interface ArticleService extends IService<Article> {
    /**
     * @Author: xingyuchen
     * @Discription: 获得所有文章的信息
     * @Date: 2021/7/19 10:21 下午
     */
    public List<ArticleDto> listArticle();



    /**
     * @Author: xingyuchen
     * @Discription: 模糊查询所有文章信息
     * @param null
     * @Date: 2021/8/15 9:47 下午
     */
    Page<ArticleDto> listAllArticleWithSearch(Page<ArticleDto> page,String name);

    /**
     * @Author: xingyuchen
     * @Discription: 列出所有的文章详细信息
     * @Date: 2021/8/4 1:01 下午
     */
    Page<ArticleDto> listAllArticle(Page<ArticleDto> page);

    /**
     * @Author: xingyuchen
     * @Discription: 模糊查询所有文章信息
     * @param null
     * @Date: 2021/8/15 9:47 下午
     */
    Page<ArticleDto> listAllArticleWithSearchDeleted(Page<ArticleDto> page,String name);

    /**
     * @Author: xingyuchen
     * @Discription: 列出所有的文章详细信息
     * @Date: 2021/8/4 1:01 下午
     */
    Page<ArticleDto> listAllArticleDeleted(Page<ArticleDto> page);

    /**
     * @Author: xingyuchen
     * @Discription: 彻底删除文章
     * @param null
     * @Date: 2021/8/20 7:16 上午
     */
    Integer clearArticleById( Integer aId);

}
