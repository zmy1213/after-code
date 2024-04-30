package com.czxy.activity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.activity.pojo.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.czxy.vo.ActivityVo;
import com.czxy.vo.SearchVo;

import java.util.List;


public interface ActivityService extends IService<Activity> {
    Page<Activity> findCondition(ActivityVo activityVo);
    Page<Activity> findCondition(ActivityVo activityVo,Integer uid);
    List<Activity> findBySid(Integer sid);
}
