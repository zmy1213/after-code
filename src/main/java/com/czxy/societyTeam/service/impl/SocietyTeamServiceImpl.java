package com.czxy.societyTeam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.czxy.societyTeam.mapper.SocietyTeamMapper;
import com.czxy.societyTeam.service.SocietyTeamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.user.mapper.UserMapper;
import com.czxy.user.pojo.User;
import com.czxy.vo.SearchVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SocietyTeamServiceImpl extends ServiceImpl<SocietyTeamMapper, SocietyTeam> implements SocietyTeamService {

    @Resource
    private SocietyTeamMapper societyTeamMapper;
    @Override
    public Page<SocietyTeam> findCondition(SearchVo searchVo) {
        Page<SocietyTeam> page = new Page<>(searchVo.getPage(), searchVo.getPageSize());
        QueryWrapper<SocietyTeam> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(searchVo.getName())){
            wrapper.like("name",searchVo.getName());

        }
        baseMapper.selectPage(page,wrapper);
        return page;
    }
    @Override
    public SocietyTeam findById(Integer id) {
        //相等于select * from user where id = ?

        return societyTeamMapper.selectById(id);
    }
}
