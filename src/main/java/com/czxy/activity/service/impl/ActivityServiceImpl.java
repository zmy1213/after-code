package com.czxy.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.activity.pojo.Activity;
import com.czxy.activity.mapper.ActivityMapper;
import com.czxy.activity.service.ActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.societyTeam.mapper.SocietyTeamMapper;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.czxy.societyTeam.service.SocietyTeamService;
import com.czxy.team_manager.service.TeamManagerService;
import com.czxy.vo.ActivityVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Resource
    private SocietyTeamMapper societyTeamMapper;
    @Resource
    private TeamManagerService teamManagerService;
    @Override
    public Page<Activity> findCondition(ActivityVo activityVo) {
        Page<Activity> page = new Page<>(activityVo.getPage(), activityVo.getPageSize());
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(activityVo.getContent())){
            wrapper.like("content",activityVo.getContent());
        }
        if (activityVo.getSId()!=null){
            wrapper.eq("s_id",activityVo.getSId());
        }

        baseMapper.selectPage(page,wrapper);
        List<Activity> records = page.getRecords();
        for (Activity record : records) {
            SocietyTeam societyTeam = societyTeamMapper.selectById(record.getSId());
            if (societyTeam!=null){
                record.setSocietyTeamName(societyTeam.getName());
            }

        }

        return page;
    }


    @Override
    public Page<Activity> findCondition(ActivityVo activityVo,Integer uid) {
        Page<Activity> page = new Page<>(activityVo.getPage(), activityVo.getPageSize());
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();


        wrapper.eq("s_id",teamManagerService.findMyTeamSid(uid));

        baseMapper.selectPage(page,wrapper);
        List<Activity> records = page.getRecords();
        for (Activity record : records) {
            SocietyTeam societyTeam = societyTeamMapper.selectById(record.getSId());
            if (societyTeam!=null){
                record.setSocietyTeamName(societyTeam.getName());
            }

        }

        return page;
    }

    @Override
    public List<Activity> findBySid(Integer sid) {
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.eq("s_id",sid);
        List<Activity> activities = baseMapper.selectList(wrapper);
        return activities;
    }
}
