package com.cxy.zjbg.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.dto.CharacterDto;
import com.cxy.zjbg.common.dto.UserDto;
import com.cxy.zjbg.entity.Character;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 红色人物表 Mapper 接口
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@Mapper
public interface CharacterMapper extends BaseMapper<Character> {

    /**
     * @Author: xingyuchen
     * @Discription: 列出所有的红色人物
     * @Date: 2021/7/21 10:52 上午
    */
    public List<CharacterDto> listCharacters();

    /**
     * @Author: xingyuchen
     * @Discription: 根据ID获取红色人物
     * @param cId
     * @Date: 2021/7/21 11:32 上午
    */
    public CharacterDto getCharacterById(@Param("cId")String cId);

    /**
     * @Author: xingyuchen
     * @Discription: 修改红色人物信息
     * @param characterDto
     * @Date: 2021/7/21 1:59 下午
    */
    public Integer updateCharacter(CharacterDto characterDto);

    /**
     * @Author: xingyuchen
     * @Discription: 新增红色人物信息
     * @param characterDto
     * @Date: 2021/7/21 1:59 下午
     */
    public Integer saveCharacter(CharacterDto characterDto);

    /**
     * @Author: xingyuchen
     * @Discription: 模糊查询所有人物信息
     * @param null
     * @Date: 2021/8/15 9:47 下午
     */
    Page<CharacterDto> listAllCharacterWithSearch(Page<CharacterDto> page, @Param("name") String name);

    /**
     * @Author: xingyuchen
     * @Discription: 列出所有的人物详细信息
     * @Date: 2021/8/4 1:01 下午
     */
    Page<CharacterDto> listAllCharacter(Page<CharacterDto> page);

    /**
     * @Author: xingyuchen
     * @Discription: 查询某一个城市的所有红色人物
     * @param null
     * @Date: 2021/8/19 11:21 下午
     */
    Page<CharacterDto> listAllCharacterByCity(Page<CharacterDto> page, @Param("cCityId") Integer cCityId);
}
