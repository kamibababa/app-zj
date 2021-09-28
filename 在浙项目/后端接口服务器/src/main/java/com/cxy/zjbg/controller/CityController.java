package com.cxy.zjbg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.constant.Constant;
import com.cxy.zjbg.common.dto.UserDto;
import com.cxy.zjbg.common.lang.Result;
import com.cxy.zjbg.entity.City;
import com.cxy.zjbg.service.CityService;
import com.cxy.zjbg.util.RedisActionUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 市级表 前端控制器
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private RedisActionUtil redisActionUtil;

    @GetMapping("/list")
    public Result listCity(){
        /**
         * @Author: xingyuchen
         * @Discription:
         * @param
         * @Date: 2021/7/19 9:39 下午
        */
        List<City> cities = cityService.list();
        if(cities.size()>0){
            return Result.succ(cities);
        }
        return Result.fail("请重新尝试");
    }

    @GetMapping("/list/with/search")
    public Result listCityWithSearch(@RequestParam(value = "page") Integer page,@RequestParam(value = "name") String name){
        if ( !name.equals("") && !name.equals(null) ){
            Page pages = new Page(page, Constant.PAGE_RECORD);
            IPage cities = cityService.page(pages, new QueryWrapper<City>().like("c_name", name));
            return Result.succ(cities);
        }
        Page pages = new Page(page, Constant.PAGE_RECORD);
        //再在数据库里根据id正序排列下来
        IPage cities = cityService.page(pages, new QueryWrapper<City>().orderByAsc("c_id"));
        return Result.succ(cities);
    }

    //@RequiresPermissions({"get"})
    @GetMapping("/{cId}/get")
    public Result getCityById(@PathVariable(name = "cId")Integer cId){
        /**
         * @Author: xingyuchen
         * @Discription: 获取到城市id对应的内容属性
         * @param cId
         * @Date: 2021/7/19 9:46 下午
        */
        return Result.succ(cityService.getById(cId));
    }

    @GetMapping("/{cId}/remove")
    public Result removeCityById(@PathVariable(name = "cId")Integer cId){
        /**
         * @Author: xingyuchen
         * @Discription: 删除某城市Id的所有内容
         * @param cId
         * @Date: 2021/7/19 9:48 下午
        */
        boolean b = cityService.removeById(cId);
        if(b){
            return Result.succ("删除成功");
        }
        return Result.succ("删除失败");
    }

    @PostMapping("/update")
    public Result updateCityById(@RequestBody City city){
        /**
         * @Author: xingyuchen
         * @Discription: 修改城市的内容
         * @param city
         * @Date: 2021/7/19 9:51 下午
        */
        boolean b = cityService.updateById(city);
        if(b){
            return Result.succ("修改成功");
        }
        return Result.fail("修改失败");
    }

    @PostMapping("/save")
    public Result saveCity(@RequestBody City city){
        /**
         * @Author: xingyuchen
         * @Discription: 增添城市内容
         * @param city
         * @Date: 2021/7/19 9:55 下午
        */
        boolean save = cityService.save(city);
        if (save){
            return Result.succ("新增成功");
        }
        return Result.fail("新增失败");
    }

    @GetMapping("/look/all")
    public Result lookAllCity(){
        List<City> cities = cityService.list();
        for (City city: cities) {
            Long aLong = redisActionUtil.lookCityFootByIdRedis(city.getCId(),0);
        }
        List list = redisActionUtil.lookAllCityFootRedis();
        return Result.succ(list);
    }

    @GetMapping("/look/clear")
    public Result removeCityFootRedis(){
        redisActionUtil.removeCityFootRedis();
        return Result.succ("清空城市足迹成功");
    }


}

