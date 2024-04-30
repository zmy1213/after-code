package com.czxy.team_manager.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.czxy.teamMoney.pojo.TeamMoney;
import com.czxy.team_manager.pojo.TeamManager;
import com.czxy.team_manager.service.TeamManagerService;
import com.czxy.user.pojo.User;
import com.czxy.user.service.UserService;
import com.czxy.util.Result;
import com.czxy.vo.TeamManagerVo;
import com.czxy.vo.TeamMoneyVo;
import com.czxy.vo.UserVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/teamManager")
@CrossOrigin
@Api(tags = "用户与社团的中间表")
public class TeamManagerController{

    @Resource
    private UserService userService;
    @Resource
    TeamManagerService teamManagerService;

    @PostMapping("findMyTeamMates/{uid}")
    public Result findMyTeamMates(@PathVariable("uid") Integer uid){

        List<User> users = teamManagerService.findMyTeamMates(uid);
        return Result.ok().data("data",users);
    }
    @PostMapping("/findCondition/{uid}")
    public Result findCondition(@RequestBody TeamManagerVo teamManageVo,Integer uid) {
        Page<TeamManager> byId = teamManagerService.findCondition(uid,teamManageVo);
        return Result.ok().data("data", byId);
    }

}
