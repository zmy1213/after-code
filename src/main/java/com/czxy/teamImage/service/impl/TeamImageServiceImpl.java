package com.czxy.teamImage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.teamImage.mapper.TeamImageMapper;
import com.czxy.teamImage.service.TeamImageService;
import com.czxy.teamImage.pojo.TeamImage;
import com.czxy.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
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
public class TeamImageServiceImpl extends ServiceImpl<TeamImageMapper, TeamImage> implements TeamImageService {



    @Resource
    TeamImageMapper teamImageMapper;
    @Override
    public Result uploadImage(MultipartFile file ,Integer uid){
        QueryWrapper<TeamImage> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);

        String filePath = "//Users/zhuzhumingyang/Downloads/images/test/" + file.getOriginalFilename();
        TeamImage teamImage = new TeamImage();
        teamImage.setUid(uid);
        teamImage.setPath(filePath);
        teamImage.setStatus(0);//0 代表申请

        List<TeamImage> lists = teamImageMapper.selectList(wrapper);
        if(lists.size()>=1){
            return Result.error().message("重复上传");
        }

        if(teamImageMapper.insert(teamImage)>0){
            if (!file.isEmpty()) {
                try {
                    // 将MultipartFile对象转换为字节数组
                    byte[] bytes = file.getBytes();

                    // 指定保存文件的路径和文件名

                    System.out.println(filePath);
                    try (FileOutputStream fos = new FileOutputStream(filePath)) {
                        fos.write(bytes);
                    }
                    return Result.ok().message("成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                return Result.error().message("文件为空");
            }
        }
        return Result.error().message("插入数据失败");
    }
}
