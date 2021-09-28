package com.cxy.zjbg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.constant.Constant;
import com.cxy.zjbg.common.dto.UserDto;
import com.cxy.zjbg.common.lang.Result;
import com.cxy.zjbg.entity.City;
import com.cxy.zjbg.entity.RedBase;
import com.cxy.zjbg.service.CityService;
import com.cxy.zjbg.service.RedBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description:
 * @Data:Created in 2021/8/20 2:27 上午
 */
@RestController
@RequestMapping("/redBase")
public class RedBaseController {

    @Autowired
    private RedBaseService redBaseService;


    @GetMapping("/{rCityId}/list")
    public Result listRedBase(@PathVariable(name = "rCityId") String rCityId){
        /**
         * @Author: xingyuchen
         * @Discription:获取所有的城市列
         * @param
         * @Date: 2021/7/19 9:39 下午
         */
        List<RedBase> redBases = redBaseService.list(new QueryWrapper<RedBase>().eq("r_city_id",rCityId));
        if(redBases.size()>0){
            return Result.succ(redBases);
        }
        return Result.fail("请重新尝试");
    }

    @GetMapping("/list/with/search")
    public Result listRedBaseWithSearch(@RequestParam(value = "page") Integer page, @RequestParam(value = "name") String name){
        /**
         * @Author: xingyuchen
         * @Discription: 分页查询所有红色基地或者模糊查询
         * @param page
         * @param name
         * @Date: 2021/8/20 2:51 上午
        */
        if ( !name.equals("") && !name.equals(null) ){
            Page pages = new Page(page, Constant.PAGE_RECORD);
            final Page page1 = redBaseService.listAllRedBaseWithSearch(pages, name);
            return Result.succ(page1);
        }
        Page pages = new Page(page, Constant.PAGE_RECORD);
        Page<RedBase> basePage = redBaseService.listAllRedBase(pages);
        return Result.succ(basePage);
    }

    @GetMapping("/{rId}/get")
    public Result getRedBaseById(@PathVariable(name = "rId")Integer rId){
        /**
         * @Author: xingyuchen
         * @Discription: 获取到基地id对应的内容属性
         * @param cId
         * @Date: 2021/7/19 9:46 下午
         */
        return Result.succ(redBaseService.getById(rId));
    }

    @GetMapping("/{rId}/remove")
    public Result removeCityById(@PathVariable(name = "rId")Integer rId){
        /**
         * @Author: xingyuchen
         * @Discription: 删除某基地Id的所有内容
         * @param cId
         * @Date: 2021/7/19 9:48 下午
         */
        boolean b = redBaseService.removeById(rId);
        if(b){
            return Result.succ("删除成功");
        }
        return Result.succ("删除失败");
    }

    @PostMapping("/update")
    public Result updateRedBaseById(@RequestBody RedBase redBase){
        /**
         * @Author: xingyuchen
         * @Discription: 修改红色基地的内容
         * @param city
         * @Date: 2021/7/19 9:51 下午
         */
        boolean b = redBaseService.updateById(redBase);
        if(b){
            return Result.succ("修改成功");
        }
        return Result.fail("修改失败");
    }

    @PostMapping("/save")
    public Result saveRedBase(@RequestBody RedBase redBase){
        /**
         * @Author: xingyuchen
         * @Discription: 增添基地内容
         * @param city
         * @Date: 2021/7/19 9:55 下午
         */
        boolean save = redBaseService.save(redBase);
        if (save){
            return Result.succ("新增成功");
        }
        return Result.fail("新增失败");
    }
}
