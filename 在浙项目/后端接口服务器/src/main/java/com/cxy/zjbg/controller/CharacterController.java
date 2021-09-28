package com.cxy.zjbg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.constant.Constant;
import com.cxy.zjbg.common.dto.CharacterDto;
import com.cxy.zjbg.common.dto.UserDto;
import com.cxy.zjbg.common.lang.Result;
import com.cxy.zjbg.entity.Character;
import com.cxy.zjbg.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 红色人物表 前端控制器
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@RestController
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/{cCityId}/list")
    public Result listCharacter(@PathVariable("cCityId") String cCityId){
        /**
         * @Author: xingyuchen
         * @Discription: 获取所有的红色人物信息
         * @param
         * @Date: 2021/7/21 1:53 下午
        */
        List<Character> characters = characterService.list(new QueryWrapper<Character>().eq("c_city_id", cCityId));
        if(characters.size()>0){
            return Result.succ(characters);
        }
        return Result.fail("人物条数为空");
    }

    @GetMapping("/list/with/search")
    public Result listWithSearch(@RequestParam(value = "page") Integer page,@RequestParam(value = "name") String name){
        /**
         * @Author: xingyuchen
         * @Discription: 查询分页或者模糊查询分页
         * @param page
         * @param name
         * @Date: 2021/8/14 7:22 下午
         */
        if ( !name.equals("") && !name.equals(null) ){
            Page pages = new Page(page, Constant.PAGE_RECORD);
            //IPage<Users> page1 = usersService.page(pages, new QueryWrapper<Users>().like("u_username",name));
            final Page page1 = characterService.listAllCharacterWithSearch(pages, name);
            return Result.succ(page1);
        }
        Page pages = new Page(page, Constant.PAGE_RECORD);
        //再在数据库里根据id正序排列下来
        //IPage pageUsers = usersService.page(pages, new QueryWrapper<Users>().orderByAsc("u_id").eq("deleted",0));
        Page<CharacterDto> pageUsers = characterService.listAllCharacter(pages);
        return Result.succ(pageUsers);
    }

    @GetMapping("/{cId}/get")
    public Result getCharacterById(@PathVariable(name = "cId") String cId){
        /**
         * @Author: xingyuchen
         * @Discription: 获取某一个红色人物信息
         * @param cId
         * @Date: 2021/7/21 1:53 下午
        */
        CharacterDto characterById = characterService.getCharacterById(cId);
        return Result.succ(characterById);
    }

    @GetMapping("/by/cityId")
    public Result getAllCharacterByCityId(@RequestParam(value = "page") Integer page,@RequestParam(value = "cCityId") String cCityId){
        /**
         * @Author: xingyuchen
         * @Discription: 根据城市id获取红色人物
         * @param page
         * @param cCityId
         * @Date: 2021/8/19 11:28 下午
        */
        Page pages = new Page(page, Constant.PAGE_RECORD);
        Page<CharacterDto> characterDtoPage = characterService.listAllCharacterByCity(pages, Integer.valueOf(cCityId));
        return Result.succ(characterDtoPage);
    }

    @GetMapping("/{cId}/remove")
    public Result removeCharacterById(@PathVariable(name = "cId") String cId){
        /**
         * @Author: xingyuchen
         * @Discription: 根据id删除红色人物
         * @param cId
         * @Date: 2021/7/21 1:56 下午
        */
        boolean b = characterService.removeById(cId);
        if(b){
            return Result.succ("删除成功");
        }
        return Result.fail("删除失败");
    }

    @PostMapping("/update")
    public Result updateCharacter(@RequestBody CharacterDto characterDto){
        /**
         * @Author: xingyuchen
         * @Discription: 根据id修改用户信息
         * @param characterDto
         * @Date: 2021/7/21 2:20 下午
        */
        Integer integer = characterService.updateCharacter(characterDto);
        if(integer>0){
            return Result.succ("修改成功");
        }
        return Result.succ("修改失败");
    }

    @PostMapping("/save")
    public Result saveCharacter(@RequestBody CharacterDto characterDto){
        /**
         * @Author: xingyuchen
         * @Discription: 新增红色人物
         * @param characterDto
         * @Date: 2021/7/21 2:24 下午
        */
        Integer integer = characterService.saveCharacter(characterDto);
        if(integer>0){
            return Result.succ("新增成功");
        }
        return Result.fail("新增失败");
    }
}

