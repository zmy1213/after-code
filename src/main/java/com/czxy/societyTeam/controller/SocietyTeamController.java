package com.czxy.societyTeam.controller;


import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.societyTeam.pojo.SocietyTeam;
import com.czxy.societyTeam.service.SocietyTeamService;
import com.czxy.teamImage.mapper.TeamImageMapper;
import com.czxy.teamImage.pojo.TeamImage;
import com.czxy.team_manager.pojo.TeamManager;
import com.czxy.team_manager.service.TeamManagerService;
import com.czxy.user.pojo.User;
import com.czxy.util.Result;
import com.czxy.vo.SearchVo;
import com.czxy.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Wrapper;
import java.text.ParseException;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/societyTeam")
@Api(tags = "社团")
@CrossOrigin
public class SocietyTeamController {

    @Resource
    private SocietyTeamService service;
    @Resource
    private TeamManagerService teamManagerService;
    @Resource
    private TeamImageMapper teamImageMapper;

    //通过id找到对应的数据
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable("id") Integer id) {
        SocietyTeam byId = service.getById(id);
        return Result.ok().data("data", byId);
    }


    //分页多条件查找数据
    @PostMapping("/findCondition/{uid}")
    public Result findCondition(@RequestBody SearchVo searchVo,@PathVariable("uid")Integer uid){
        Integer sid = teamManagerService.findMyTeamSid(uid);
        SocietyTeam byId = service.getById(sid);
        Page<SocietyTeam> res = new Page<>();
        List<SocietyTeam> sTList = new ArrayList<>();
        sTList.add(byId);
        res.setRecords(sTList);
        return Result.ok().data("data", res);
    }

    //分页多条件查找数据
    @PostMapping("/findCondition")
    public Result findCondition(@RequestBody SearchVo searchVo){
        Page<SocietyTeam> byId = service.findCondition(searchVo);
        return Result.ok().data("data", byId);
    }


    //查找所有数据
    @GetMapping("/findAll")
    public Result findAll() {
        List<SocietyTeam> byId = service.list();
        return Result.ok().data("data", byId);
    }

    //修改
    @PutMapping("/updateData")
    public Result update(@RequestBody SocietyTeam societyTeam) {
        Boolean b = service.updateById(societyTeam);
        if (b) {
            return Result.ok().message("修改成功");
        }
        return Result.error().message("修改失败");
    }

    //添加
    @PostMapping("/addData")
    public Result addData(@RequestBody SocietyTeam societyTeam) {
        societyTeam.setCreateTime(new Date());
        Boolean save = service.save(societyTeam);
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
    @GetMapping("/getImage/{sid}")
    public ResponseEntity<byte[]> getImage(@PathVariable("sid") Integer sid) throws IOException {


        QueryWrapper<TeamImage> wrapper = new QueryWrapper<>();

        Integer uid = teamManagerService.findUidBySid(sid);

        wrapper.eq("uid",uid);
        String filename = teamImageMapper.selectOne(wrapper).getPath();
        System.out.println(filename);
        final InputStream in = new FileInputStream(filename);


        // 设置响应头，告诉浏览器以流的形式返回图片
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        // 返回图片资源
        return ResponseEntity.ok()
                .headers(headers)
                .body(IOUtils.toByteArray(in));
    }
}

