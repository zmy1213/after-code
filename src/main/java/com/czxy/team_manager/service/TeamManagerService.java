package com.czxy.team_manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.teamMoney.pojo.TeamMoney;
import com.czxy.team_manager.pojo.TeamManager;
import com.czxy.user.pojo.User;
import com.czxy.userTeam.pojo.UserTeam;
import com.czxy.vo.TeamManagerVo;
import com.czxy.vo.TeamMoneyVo;
import io.swagger.models.auth.In;

import java.util.List;

public interface TeamManagerService extends IService<TeamManager> {

    List<User> findMyTeamMates(Integer uid);
    Integer findMyTeamSid(Integer uid);
    List<UserTeam> findMyTeamUserIds(Integer sid);

    Page<TeamManager> findCondition(Integer uid,TeamManagerVo teamManageVo);

    Integer findUidBySid(Integer sid);

}
