package com.czxy.createTeamApplication.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.createTeamApplication.pojo.CreateTeamApplication;
import com.czxy.userTeam.pojo.UserTeam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.util.Result;
import com.czxy.vo.CreateTeamApplicationVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2024-03-01
 */
public interface CreateTeamApplicationService extends IService<CreateTeamApplication> {
    public Page<CreateTeamApplication> findCondition(CreateTeamApplicationVo createTeamApplicationVo, Integer uid);

    public String refuseTeamApplication(Integer uid);

    public String deleteTeamApplication(Integer uid);

    Result findAll();

}
