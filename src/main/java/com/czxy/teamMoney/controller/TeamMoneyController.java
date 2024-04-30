package com.czxy.teamMoney.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.czxy.societyTeam.service.SocietyTeamService;
import com.czxy.teamMoney.pojo.TeamMoney;
import com.czxy.teamMoney.service.TeamMoneyService;
import com.czxy.util.Result;
import com.czxy.vo.SearchVo;
import com.czxy.vo.TeamMoneyVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/teamMoney")
@Api(tags = "社团财务")
@CrossOrigin
public class TeamMoneyController {

    @Resource
    private TeamMoneyService service;


    //通过id找到对应的数据
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable("id") Integer id) {
        TeamMoney byId = service.getById(id);
        return Result.ok().data("data", byId);
    }


    //分页多条件查找数据
    @PostMapping("/findCondition")
    public Result findCondition(@RequestBody TeamMoneyVo teamMoneyVo) {
        Page<TeamMoney> byId = service.findCondition(teamMoneyVo);
        return Result.ok().data("data", byId);
    }

    @PostMapping("/findCondition/{uid}")
    public Result findCondition(@RequestBody TeamMoneyVo teamMoneyVo,@PathVariable Integer uid) {
        Page<TeamMoney> byId = service.findCondition(teamMoneyVo,uid);
        return Result.ok().data("data", byId);
    }
    @PostMapping("/findConditionByUid/{uid}")
    public Result findConditionByUid(@RequestBody TeamMoneyVo teamMoneyVo,@PathVariable Integer uid) {
        Page<TeamMoney> byId = service.findConditionByUid(teamMoneyVo,uid);
        return Result.ok().data("data", byId);
    }
    //查找所有数据
    @GetMapping("/findAll")
    public Result findAll() {
        List<TeamMoney> byId = service.list();
        return Result.ok().data("data", byId);
    }

    //缴纳团费
    @GetMapping("/pay/{id}")
    public Result pay(@PathVariable("id") Integer id) {
        service.pay(id);
        return Result.ok();
    }

    //修改
    @PutMapping("/updateData")
    public Result update(@RequestBody TeamMoney teamMoney) {
        Boolean b = service.updateById(teamMoney);
        if (b) {
            return Result.error().message("修改成功");
        }
        return Result.ok().message("修改失败");
    }

    //添加
    @PostMapping("/addData")
    public Result addData(@RequestBody TeamMoney teamMoney) {
        teamMoney.setState("0");
        Boolean save = service.save(teamMoney);
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

