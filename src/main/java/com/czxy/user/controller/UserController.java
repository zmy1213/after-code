package com.czxy.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.czxy.team_manager.service.TeamManagerService;
import com.czxy.user.pojo.User;
import com.czxy.user.service.UserService;
import com.czxy.util.Result;
import com.czxy.vo.UserVo;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Resource
    private UserService service;
    @Resource
    private TeamManagerService teamManagerService;


    //登录方法
    @PostMapping("/login")
    public Result login(@RequestBody User user) throws UnknownHostException {
            User login = service.login(user);
            //如果login不为null，说明登录成功
            if (login != null) {
                //返回给前端数据，用户的id，用户，用户的权限所能看见的页面，用户的权限，
                return Result.ok().data("id", login.getId()).data("role", login.getRoleId()).message("登录成功");
            }
            return Result.error().message("账号或密码错误！");
    }

    //通过id找到对应的数据
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable("id") Integer id) throws ParseException {
        User byId = service.findById(id);
        return Result.ok().data("data", byId);
    }

    //通过id找到对应的数据
    @GetMapping("/userJoin/{uid}/{sid}")
    public Result userJoin(@PathVariable("uid") Integer uid,@PathVariable("sid") Integer sid)  {
        Boolean b = service.userJoin(uid, sid);

        if (b) {
            return Result.ok().message("入团成功！！！");
        }
        return Result.error().message("已入团，请勿重复加团！！！");
    }

    //分页查找用户数据
    @PostMapping("/findJoinTeam")
    public Result findJoinTeam(@RequestBody UserVo userVo){
        Page<SocietyTeam> byId = service.findJoinTeam(userVo);
        return Result.ok().data("data", byId);
    }

    //查找所有管理员
    @GetMapping("/findAllAdmin")
    public Result findAllAdmin() {
        List<User> byId = service.findAllAdmin();
        return Result.ok().data("data", byId);
    }

    //查找所有客户
    @GetMapping("/findAllUser")
    public Result findAllUser() {
        List<User> byId = service.findAllUser();
        return Result.ok().data("data", byId);
    }


    //查找所有数据
    @GetMapping("/findAll")
    public Result findAll() {
        List<User> byId = service.list();
        return Result.ok().data("data", byId);
    }
    @PostMapping("/findCondition")
    public Result findCondition(@RequestBody UserVo userVo){
        Page<User> byId = service.findCondition(userVo);
        return Result.ok().data("data", byId);
    }
    //分页查找用户数据
    @PostMapping("/findCondition/{uid}")
    public Result findCondition(@RequestBody UserVo userVo, @PathVariable("uid") Integer uid){
        Page<User> byId = new Page<>();
        if(userVo.getRoleId()==3) {
            System.out.println(uid);
            List<User> res = teamManagerService.findMyTeamMates(uid);
            byId.setRecords(res);
        }else{
            return Result.error().message("error");
        }
        return Result.ok().data("data", byId);

    }


    //修改用户信息
    @PutMapping("/updateData")
    public Result update(@RequestBody User user) {
        Integer b = service.updateUser(user);
        if (b == 0) {
            return Result.error().message("有相同的账号和密码，修改失败");
        } else if (b == 1) {
            return Result.ok().message("修改成功");
        }
        return Result.ok().message("添加成功");
    }

    //注册用户信息
    @PostMapping("/register")
    public Result register(@RequestBody User user) {

        Integer save = service.register(user);
        if (save == 1) {
            return Result.ok().message("注册成功");
        }
        return Result.error().message("有相同的账号,注册失败");
    }

    //删除用户信息
    @DeleteMapping("/deleteById/{id}")
    public Result register(@PathVariable("id") Integer id) {
        Boolean save = service.removeById(id);
        if (save) {
            return Result.ok().message("删除成功");
        }
        return Result.error().message("删除失败");
    }
    @DeleteMapping("/deletePersonFromSocial/{uid}/{sid}")
    public Result deletePerFromSol(@PathVariable("uid") Integer uid,@PathVariable("sid") Integer sid){
        Boolean mes = service.deletePersonFromSocial(uid,sid);
        if(mes){
            return Result.ok().message("在社团中成功删除此人");
        }
        return Result.error().message("在社团中删除此人失败");
    }

}

