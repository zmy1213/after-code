package com.czxy.teamImage.service;

import com.czxy.teamImage.pojo.TeamImage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.util.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author
 * @since 2024-03-01
 */
public interface TeamImageService extends IService<TeamImage> {
    Result uploadImage(MultipartFile file, Integer uid);

}