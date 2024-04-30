package com.czxy.vo;


import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class ActivityVo {
    private Integer sId;
    private String content;
    private Integer page;
    private Integer pageSize;
}
