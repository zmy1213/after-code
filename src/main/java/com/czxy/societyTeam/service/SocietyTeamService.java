package com.czxy.societyTeam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.vo.SearchVo;


public interface SocietyTeamService extends IService<SocietyTeam> {

    Page<SocietyTeam> findCondition(SearchVo searchVo);
    SocietyTeam findById(Integer id);

}
