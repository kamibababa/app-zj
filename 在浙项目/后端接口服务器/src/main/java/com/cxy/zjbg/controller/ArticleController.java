package com.cxy.zjbg.controller;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.constant.Constant;
import com.cxy.zjbg.common.dto.ArticleDto;
import com.cxy.zjbg.common.lang.Result;
import com.cxy.zjbg.entity.Article;
import com.cxy.zjbg.entity.RedBase;
import com.cxy.zjbg.service.ArticleService;
import com.cxy.zjbg.util.RedisActionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisActionUtil redisActionUtil;

    @GetMapping("/list/with/search")
    public Result listArticleWithSearch(@RequestParam(value = "page") Integer page, @RequestParam(value = "name") String name){
        /**
         * @Author: xingyuchen
         * @Discription: 分页查询所有文章或者模糊查询
         * @param page
         * @param name
         * @Date: 2021/8/20 2:51 上午
         */
        if ( !name.equals("") && !name.equals(null) ){
            Page pages = new Page(page, Constant.PAGE_RECORD);
            final Page page1 = articleService.listAllArticleWithSearch(pages, name);
            return Result.succ(page1);
        }
        Page pages = new Page(page, Constant.PAGE_RECORD);
        Page<ArticleDto> basePage = articleService.listAllArticle(pages);
        return Result.succ(basePage);
    }
    @GetMapping("/list/with/search/deleted")
    public Result listArticleWithSearchDeleted(@RequestParam(value = "page") Integer page, @RequestParam(value = "name") String name){
        /**
         * @Author: xingyuchen
         * @Discription: 分页查询所有文章或者模糊查询
         * @param page
         * @param name
         * @Date: 2021/8/20 2:51 上午
         */
        if ( !name.equals("") && !name.equals(null) ){
            Page pages = new Page(page, Constant.PAGE_RECORD);
            final Page page1 = articleService.listAllArticleWithSearchDeleted(pages, name);
            return Result.succ(page1);
        }
        Page pages = new Page(page, Constant.PAGE_RECORD);
        Page<ArticleDto> basePage = articleService.listAllArticleDeleted(pages);
        return Result.succ(basePage);
    }

    @GetMapping("/list/msg")
    public Result listArticleAllMsg(@RequestParam(value = "page") Integer page, @RequestParam(value = "name") String name){
        /**
         * @Author: xingyuchen
         * @Discription: 获取文章的所有信息,redis分页了
         * @param page
         * @Date: 2021/8/20 6:35 上午
        */
        Page<ArticleDto> allMessageRedis = redisActionUtil.getAllMessageRedis(page,name);
        return Result.succ(allMessageRedis);
    }

    @GetMapping("/list/2")
    public Result listArticleType2(){
        return Result.succ(articleService.list(new QueryWrapper<Article>().eq("a_type",2)));
    }

    @GetMapping("/list/3")
    public Result listArticleType3(){
        return Result.succ(articleService.list(new QueryWrapper<Article>().eq("a_type",3)));
    }
    @GetMapping("/list/4")
    public Result listArticleType4(){
        return Result.succ(articleService.list(new QueryWrapper<Article>().eq("a_type",4)));
    }

    @GetMapping("/list/city")
    public Result listArticle(){
        /**
         * @Author: xingyuchen
         * @Discription: 查询所有文章
         * @param
         * @Date: 2021/7/19 10:43 下午
         */
        List<Article> list = articleService.list();
        if (list.size()>0){
            return Result.succ(list);
        }
        return Result.fail("文章记录为空");
    }

    @GetMapping("/list/with/city")
    public Result listArticleWithCity(){
        /**
         * @Author: xingyuchen
         * @Discription: 查询所有文章联表城市
         * @param
         * @Date: 2021/7/19 10:43 下午
        */
        List<ArticleDto> articleDtos = articleService.listArticle();
        if (articleDtos.size()>0){
            return Result.succ(articleDtos);
        }
        return Result.fail("文章记录为空");
    }

    @GetMapping("/{aId}/get")
    public Result getArticleById(@PathVariable(name = "aId")Integer aId){
        /**
         * @Author: xingyuchen
         * @Discription: 获取某一Id的文章(市名用ajax再获取一次)
         * @param aId
         * @Date: 2021/7/19 11:23 下午
        */
        Article article = articleService.getById(aId);
        return Result.succ(article);
    }

    @GetMapping("/{aId}/remove")
    public Result removeArticleById(@PathVariable(name = "aId")Integer aId){
        /**
         * @Author: xingyuchen
         * @Discription: 删除某一ID的文章
         * @param aId
         * @Date: 2021/7/19 11:27 下午
        */
        boolean b = articleService.removeById(aId);
        if(b){
            return Result.succ("删除成功");
        }
        return Result.fail("删除失败");
    }

    @GetMapping("/{aId}/clear")
    public Result clearArticleById(@PathVariable(name = "aId")Integer aId){
        /**
         * @Author: xingyuchen
         * @Discription: 删除某一ID的文章
         * @param aId
         * @Date: 2021/7/19 11:27 下午
         */
        Integer b = articleService.clearArticleById(aId);
        if(b>0){
            return Result.succ("删除成功");
        }
        return Result.fail("删除失败");
    }

    @PostMapping("/update")
    public Result updateArticle(@RequestBody Article article){
        /**
         * @Author: xingyuchen
         * @Discription: 修改文章信息
         * @param articleDto
         * @Date: 2021/7/20 12:09 上午
        */
        boolean b = articleService.updateById(article);
        if(b){
            return Result.succ("修改成功");
        }
        return Result.fail("修改失败");
    }

    @PostMapping("/save")
    public Result saveArticle(@RequestBody Article article){
        /**
         * @Author: xingyuchen
         * @Discription: 新增文章信息
         * @param articleDto
         * @Date: 2021/7/20 11:35 上午
        */
        boolean save = articleService.save(article);
        if (save){
            return Result.succ("新增成功");
        }
        return Result.fail("新增失败");
    }

    @GetMapping("/{aId}/recover")
    public Result recoverArticle(@PathVariable(value = "aId")Integer aId){
        /**
         * @Author: xingyuchen
         * @Discription: 回收站
         * @param
         * @Date: 2021/8/20 7:01 上午
         */
        Article article = new Article();
        article.setAId(aId);
        article.setDeleted(0);
        boolean b = articleService.updateById(article);
        if(b){
            return Result.succ("撤回成功");
        }
        return Result.fail("撤回失败");
    }



    @GetMapping("/{aId}/click/save")
    public Result saveArticleRedis(@PathVariable(name = "aId")@RequestBody String aId, String uId){
        /**
         * @Author: xingyuchen
         * @Discription: 点赞功能
         * @param aId
         * @param uId
         * @Date: 2021/7/20 9:21 下午
        */
        Long aLong = redisActionUtil.saveArticleClickStarRedis(aId, uId);
        return Result.succ(aLong);
    }

    @GetMapping("/{aId}/without/click/save")
    public Result saveArticleRedis(@PathVariable(name = "aId")@RequestBody String aId){
        /**
         * @Author: xingyuchen
         * @Discription: 点赞功能
         * @param aId
         * @param uId
         * @Date: 2021/7/20 9:21 下午
         */
        Long aLong = redisActionUtil.saveArticleClickStarWithoutClickRedis(aId);
        return Result.succ(aLong);
    }

    @GetMapping("/{aId}/click/remove")
    public Result removeArticleRedis(@PathVariable(name = "aId")@RequestBody String aId, String uId){
        /**
         * @Author: xingyuchen
         * @Discription: 取消点赞功能
         * @param aId
         * @param uId
         * @Date: 2021/7/20 9:39 下午
        */
        Long aLong = redisActionUtil.removeArticleClickStarRedis(aId, uId);
        return Result.succ(aLong);
    }

    @GetMapping("/{aId}/look")
    public Result lookArticleRedis(@PathVariable(name = "aId")@RequestBody String aId, String uId){
        /**
         * @Author: xingyuchen
         * @Discription: 文章总浏览量
         * @param aId
         * @param uId
         * @Date: 2021/7/20 9:40 下午
        */
        Long aLong = redisActionUtil.lookArticleStarRedis(aId, uId);
        return Result.succ(aLong);
    }

    @GetMapping("/{aId}/lookWithoutClick")
    public Result lookArticleRedisWithoutClick(@PathVariable(name = "aId")String aId){
        Long aLong = redisActionUtil.lookArticleStarWithoutClick(aId);
        return Result.succ(aLong);
    }

    @GetMapping("/{aCityId}/list")
    public Result listByCId(@PathVariable(name = "aCityId")Integer aCityId){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<Article>().eq("a_city_id", aCityId);
        queryWrapper.eq("a_type",1);
        List<Article> list = articleService.list(queryWrapper);
        if(list.size()>0){
            return Result.succ(list);
        }
        return Result.fail("查询条目为空");
    }
}

