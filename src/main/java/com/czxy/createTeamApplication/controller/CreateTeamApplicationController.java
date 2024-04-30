package com.czxy.createTeamApplication.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.activity.pojo.Activity;
import com.czxy.applySociety.service.ApplySocietyService;
import com.czxy.createTeamApplication.pojo.CreateTeamApplication;
import com.czxy.createTeamApplication.service.CreateTeamApplicationService;
import com.czxy.util.Result;
import com.czxy.vo.ActivityVo;
import com.czxy.vo.CreateTeamApplicationVo;
import com.czxy.vo.SocietyTeamVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author
 * @since 2024-03-01
 */
@RestController
@RequestMapping("/createTeam")
@CrossOrigin

public class CreateTeamApplicationController {

    @Resource
    CreateTeamApplicationService createTeamApplicationService;
    @Resource
    ApplySocietyService applySocietyService;


    @PostMapping("/findCondition/{uid}")
    public Result findCondition(@RequestBody CreateTeamApplicationVo createTeamApplicationVo, @PathVariable Integer uid){
        Page<CreateTeamApplication> byId = createTeamApplicationService.findCondition(createTeamApplicationVo,uid);
        return Result.ok().data("data", byId);
    }



    @PostMapping("/createTeamApplication/{uid}")
    public Result createTeamApplication(@PathVariable("uid") Integer uid, @RequestBody SocietyTeamVo teamInfo) throws ParseException {
        return Result.ok().message(applySocietyService.createTeamApplicationAndCheckIsCreated(uid,teamInfo));
    }

    @PostMapping("/createDeleteTeamApplication/{uid}")
    public Result createDealTeamApplication(@PathVariable("uid") Integer uid, @RequestBody SocietyTeamVo teamInfo) throws ParseException {
        return Result.ok().message(applySocietyService.createDeleteTeamApplicationAndCheckIsCreated(uid,teamInfo));
    }
    @DeleteMapping("/deleteTeamApplication/{uid}")
    public Result deleteTeamApplication(@PathVariable("uid") Integer uid){
        return Result.ok().message(createTeamApplicationService.deleteTeamApplication(uid));
    }

    @GetMapping("/refuseTeamApplication/{uid}")
    public Result refuseTeamApplication(@PathVariable("uid") Integer uid){
        return Result.ok().message(createTeamApplicationService.refuseTeamApplication(uid));
    }


    @GetMapping("/findAll")
    public Result findAll(){
        return createTeamApplicationService.findAll();
    }
}

