package com.cxy.zjbg.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy.zjbg.common.dto.CharacterDto;
import com.cxy.zjbg.common.dto.UserDto;
import com.cxy.zjbg.entity.Character;
import com.cxy.zjbg.mapper.CharacterMapper;
import com.cxy.zjbg.service.CharacterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 红色人物表 服务实现类
 * </p>
 *
 * @author cxy
 * @since 2021-07-13
 */
@Service
public class CharacterServiceImpl extends ServiceImpl<CharacterMapper, Character> implements CharacterService {

    @Autowired
    private CharacterMapper characterMapper;

    @Override
    public List<CharacterDto> listCharacters() {
        return characterMapper.listCharacters();
    }

    @Override
    public CharacterDto getCharacterById(String cId) {
        return characterMapper.getCharacterById(cId);
    }

    @Override
    public Integer updateCharacter(CharacterDto characterDto) {
        return characterMapper.updateCharacter(characterDto);
    }

    @Override
    public Integer saveCharacter(CharacterDto characterDto) {
        return characterMapper.saveCharacter(characterDto);
    }

    @Override
    public Page<CharacterDto> listAllCharacterWithSearch(Page<CharacterDto> page, String name) {
        return characterMapper.listAllCharacterWithSearch(page,name);
    }

    @Override
    public Page<CharacterDto> listAllCharacter(Page<CharacterDto> page) {
        return characterMapper.listAllCharacter(page);
    }

    @Override
    public Page<CharacterDto> listAllCharacterByCity(Page<CharacterDto> page, Integer cCityId) {
        return characterMapper.listAllCharacterByCity(page,cCityId);
    }
}
