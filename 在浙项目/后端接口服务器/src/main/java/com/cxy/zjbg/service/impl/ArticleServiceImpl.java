package com.cxy.zjbg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.dto.ArticleDto;
import com.cxy.zjbg.entity.Article;
import com.cxy.zjbg.mapper.ArticleMapper;
import com.cxy.zjbg.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<ArticleDto> listArticle() {
        return articleMapper.listArticle();
    }


    @Override
    public Page<ArticleDto> listAllArticleWithSearch(Page<ArticleDto> page, String name) {
        return articleMapper.listAllArticleWithSearch(page,name);
    }

    @Override
    public Page<ArticleDto> listAllArticle(Page<ArticleDto> page) {
        return articleMapper.listAllArticle(page);
    }

    @Override
    public Page<ArticleDto> listAllArticleWithSearchDeleted(Page<ArticleDto> page, String name) {
        return articleMapper.listAllArticleWithSearchDeleted(page,name);
    }

    @Override
    public Page<ArticleDto> listAllArticleDeleted(Page<ArticleDto> page) {
        return articleMapper.listAllArticleDeleted(page);
    }

    @Override
    public Integer clearArticleById(Integer aId) {
        return articleMapper.clearArticleById(aId);
    }
}
