package com.czxy.applySociety.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.applySociety.pojo.applySociety;
import com.czxy.applySociety.service.ApplySocietyService;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.czxy.societyTeam.service.SocietyTeamService;
import com.czxy.user.pojo.User;
import com.czxy.user.service.UserService;
import com.czxy.userTeam.pojo.UserTeam;
import com.czxy.userTeam.service.UserTeamService;
import com.czxy.util.Result;
import com.czxy.vo.*;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.UnknownHostException;
import java.text.ParseException;

@RestController
@RequestMapping("/apply")
@CrossOrigin



public class ApplySocietyController {
    @Resource
    private ApplySocietyService service;
    @Resource
    private SocietyTeamService societyTeamService;
    @Resource
    private UserService userservice;
    @PostMapping("/applying/{uid}/{sid}")
    public Result applyJoinSociety(@PathVariable("uid") Integer uid, @PathVariable("sid") Integer sid, @RequestBody ApplyReason reason) throws UnknownHostException {

        User resUser = userservice.findById(uid);
        SocietyTeam resUserTeam = societyTeamService.findById(sid);
        return Result.ok().message(service.applyJoinSociety(uid,sid,reason.getReason(),resUser.getUsername(),resUserTeam.getName(),resUser.getClassname()));
    }

    @PostMapping("/findCondition")
    public Result findCondition(@RequestBody SearchVo applyVo){
        Page<applySociety> byId = service.findCondition(applyVo);

        return Result.ok().data("data", byId);
    }
    @PostMapping("/findCondition/{id}")
    public Result findCondition(@RequestBody SearchVo applyVo,@PathVariable("id") Integer uid){
        Page<applySociety> byId = service.findCondition(applyVo,uid);

        return Result.ok().data("data", byId);
    }
    @PostMapping("/findConditionApply")
    public Result findConditionApply(@RequestBody ApplySocietyVo applySociety){
        Page<applySociety> byId = service.findConditionApply(applySociety);

        return Result.ok().data("data", byId);
    }
    @PostMapping("/findConditionApplyById/{uid}")
    public Result findConditionApplyById(@RequestBody ApplySocietyVo applySociety, @PathVariable Integer uid){
        Page<applySociety> byId = service.findConditionApplyById(applySociety,uid);

        return Result.ok().data("data", byId);
    }
    @PostMapping("/findConditionApplyBySid/{uid}")
    public Result findConditionApplyBySid(@RequestBody ApplySocietyVo applySociety, @PathVariable Integer uid){
        Page<applySociety> byId = service.findConditionApplyBySid(applySociety,uid);

        return Result.ok().data("data", byId);
    }
    @PostMapping("/findManCondition/{uid}")
    public Result findManCondition(@RequestBody SearchVo applyVo,@PathVariable("uid") Integer uid){
        Page<applySociety> byId = service.findManCondition(applyVo,uid);
        return Result.ok().data("data", byId);
    }
    @PostMapping("/joinTeamById/{uid}/{sid}")
    public Result joinTeamById(@PathVariable("uid") Integer uid,@PathVariable("sid") Integer sid){

        String s;
        if(userservice.userJoin(uid,sid)&&service.joinTeamById(uid,sid)){
            return Result.ok().message("加入社团成功");
        }
        return Result.error().message("加入社团失败");
    }
    @GetMapping("createTeamById")
    public Result createTeamById(@PathVariable("uid") Integer uid,@PathVariable("sid") Integer sid){

        String s;
        if(userservice.userJoin(uid,sid)&&service.joinTeamById(uid,sid)){
            return Result.ok().message("加入社团成功");
        }
        return Result.error().message("加入社团失败");
    }
    @DeleteMapping("/refuseTeamByUidAndSid/{uid}/{sid}")
    public Result refuseTeamByUidAndSid(@PathVariable("uid") Integer uid,@PathVariable("sid") Integer sid){
        if(service.refuseApplyById(uid,sid)){
            return Result.ok().message("拒绝申请成功");
        }
        return Result.error().message("拒绝申请失败");
    }
    @DeleteMapping("/deleteTeamByUidAndSid/{uid}/{sid}")
    public Result deleteTeamByUidAndSid(@PathVariable("uid") Integer uid,@PathVariable("sid") Integer sid){
        if(service.deleteApplyById(uid,sid)){
            return Result.ok().message("删除申请成功");
        }
        return Result.error().message("删除申请失败");
    }
    @PostMapping("/createTeamApplication/{uid}")
    public Result createTeamApplication(@PathVariable("uid") Integer uid,@RequestBody SocietyTeamVo teamInfo) throws ParseException {
        return Result.ok().message(service.createTeamApplicationAndCheckIsCreated(uid,teamInfo));
    }



    @GetMapping("/createTeam/{uid}")
    public Result createTeam(@PathVariable("uid") Integer uid) throws ParseException {
        if(service.createTeam(uid)){
            return Result.ok().message("success");
        }
        return  Result.error().message("fail");
    }
    @PostMapping("/findTeamName/{uid}")
    public String findTeamName(@PathVariable Integer uid) {
        return service.findTeamName(uid);
    }
    @DeleteMapping("/deleteTeam/{uid}")
    public Result deleteTeam(@PathVariable Integer uid) {
        if (service.deleteTeam(uid)) {
            return Result.ok().message("success");
        }
        return Result.error().message("fail");
    }



}

