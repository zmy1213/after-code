package com.czxy.teamImage.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.applySociety.service.ApplySocietyService;
import com.czxy.teamImage.pojo.TeamImage;
import com.czxy.teamImage.service.TeamImageService;
import com.czxy.teamMoney.pojo.TeamMoney;
import com.czxy.teamMoney.service.TeamMoneyService;
import com.czxy.team_manager.service.TeamManagerService;
import com.czxy.util.Result;

import io.swagger.annotations.Api;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.jni.File;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/image")
@CrossOrigin

public class TeamImageController {

    @Resource
    TeamImageService teamImageService;

    @PostMapping("/upload/{uid}")
    public Result uploadFile(@RequestParam("file") MultipartFile file,@PathVariable Integer uid) {
        return teamImageService.uploadImage(file,uid);
    }
};

