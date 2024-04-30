package com.czxy.createTeamApplication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.activity.pojo.Activity;
import com.czxy.createTeamApplication.mapper.CreateTeamApplicationMapper;
import com.czxy.createTeamApplication.pojo.CreateTeamApplication;
import com.czxy.createTeamApplication.service.CreateTeamApplicationService;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.czxy.user.pojo.User;
import com.czxy.userTeam.pojo.UserTeam;
import com.czxy.userTeam.mapper.UserTeamMapper;
import com.czxy.userTeam.service.UserTeamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.util.Result;
import com.czxy.vo.ActivityVo;
import com.czxy.vo.CreateTeamApplicationVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2024-03-01
 */
@Service
public class CreateTeamApplicationServiceImpl extends ServiceImpl<CreateTeamApplicationMapper, CreateTeamApplication> implements CreateTeamApplicationService{

    @Resource
    CreateTeamApplicationMapper createTeamApplicationMapper;
    @Override
    public Page<CreateTeamApplication> findCondition(CreateTeamApplicationVo createTeamApplicationVo, Integer uid) {
        Page<CreateTeamApplication> page = new Page<>(createTeamApplicationVo.getPage(), createTeamApplicationVo.getPageSize());
        QueryWrapper<CreateTeamApplication> wrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(createTeamApplicationVo.getTeamname())) {
            wrapper.like("teamname", createTeamApplicationVo.getTeamname());
        }

        if (StringUtils.isNotBlank(createTeamApplicationVo.getUsername())) {
            System.out.println(createTeamApplicationVo.getUsername());
            wrapper.like("username", createTeamApplicationVo.getUsername());
        }
        baseMapper.selectPage(page,wrapper);
        return page;
    }

    @Override
    public Result findAll() {
        QueryWrapper<CreateTeamApplication> wrapper = new QueryWrapper<>();
        List<CreateTeamApplication> res = baseMapper.selectList(wrapper);
        return Result.ok().data("data",res);
    }

    @Override
    public String refuseTeamApplication(Integer uid){
        QueryWrapper<CreateTeamApplication> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        CreateTeamApplication createTeamApplication = new CreateTeamApplication();
        createTeamApplication.setStatus(2);
        if(createTeamApplicationMapper.update(createTeamApplication,wrapper)>0){
            return "success";
        }
        return "fail";
    }
    @Override
    public String deleteTeamApplication(Integer uid){
        QueryWrapper<CreateTeamApplication> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        if(createTeamApplicationMapper.delete(wrapper)>0){
            return "success";
        };
        return "fail";
    }
}
