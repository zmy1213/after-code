package com.czxy.applySociety.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.applySociety.pojo.applySociety;
import com.czxy.createTeamApplication.pojo.CreateTeamApplication;
import com.czxy.vo.*;
import io.swagger.models.auth.In;

import java.text.ParseException;

public interface ApplySocietyService extends IService<applySociety> {
    String applyJoinSociety(Integer sid,Integer uid ,String reason,String userName,String teamName,String classname);
    Page<applySociety> findCondition(SearchVo applyVo);
    Page<applySociety> findCondition(SearchVo applyVo,Integer uid);

    Page<applySociety> findConditionApply(ApplySocietyVo applySocietyVo);
    Page<applySociety> findConditionApplyById(ApplySocietyVo applySociety,Integer uid);

    Page<applySociety> findConditionApplyBySid(ApplySocietyVo applySociety,Integer uid);

    boolean joinTeamById(Integer uid,Integer sid);
    boolean refuseApplyById(Integer uid,Integer sid);
    boolean deleteApplyById(Integer uid,Integer sid);

    String  createTeamApplicationAndCheckIsCreated(Integer uid, SocietyTeamVo teamInfo) throws ParseException;
    String createDeleteTeamApplicationAndCheckIsCreated (Integer uid, SocietyTeamVo teamInfo) throws ParseException;
    boolean createTeam(Integer uid);
    boolean isCreated(Integer uid);
    Page<applySociety> findManCondition(SearchVo applyVo,Integer sid);
    String findTeamName(Integer uid);
    boolean deleteTeam(Integer id);


}
