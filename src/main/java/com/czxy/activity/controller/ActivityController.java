package com.czxy.activity.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.activity.pojo.Activity;
import com.czxy.activity.service.ActivityService;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.czxy.societyTeam.service.SocietyTeamService;
import com.czxy.util.Result;
import com.czxy.vo.ActivityVo;
import com.czxy.vo.SearchVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/activity")
@Api(tags = "社团活动")
@CrossOrigin
public class ActivityController {
    @Resource
    private ActivityService service;


    //通过id找到对应的数据
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable("id") Integer id) {
        Activity byId = service.getById(id);
        return Result.ok().data("data", byId);
    }

    //通过id找到对应的数据
    @GetMapping("/findBySid/{sid}")
    public Result findBySid(@PathVariable("sid") Integer sid) {
        List<Activity> byId = service.findBySid(sid);
        return Result.ok().data("data", byId);
    }


    //分页多条件查找数据
    @PostMapping("/findCondition")
    public Result findCondition(@RequestBody ActivityVo activityVo){
        Page<Activity> byId = service.findCondition(activityVo);
        return Result.ok().data("data", byId);
    }

    @PostMapping("/findCondition/{uid}")
    public Result findCondition(@RequestBody ActivityVo activityVo,@PathVariable Integer uid){
        Page<Activity> byId = service.findCondition(activityVo,uid);
        return Result.ok().data("data", byId);
    }


    //查找所有数据
    @GetMapping("/findAll")
    public Result findAll() {
        List<Activity> byId = service.list();
        return Result.ok().data("data", byId);
    }

    //修改
    @PutMapping("/updateData")
    public Result update(@RequestBody Activity activity) {
        if (activity.getCreateTime().before(new Date())){
            return Result.error().message("活动开始时间应晚于当前时间");
        }
        Boolean b = service.updateById(activity);
        if (b) {
            return Result.ok().message("修改成功");
        }
        return Result.error().message("修改失败");
    }

    //添加
    @PostMapping("/addData")
    public Result addData(@RequestBody Activity activity) {
        if (activity.getCreateTime().before(new Date())){
            return Result.error().message("活动开始时间应晚于当前时间");
        }
        Boolean save = service.save(activity);
        if (save) {
            return Result.ok().message("添加成功");
        }
        return Result.error().message("添加失败");
    }

    //删除
    @DeleteMapping("/deleteById/{id}")
    public Result register(@PathVariable("id") Integer id) {
        Boolean save = service.removeById(id);
        if (save) {
            return Result.ok().message("删除成功");
        }
        return Result.error().message("删除失败");
    }
}

