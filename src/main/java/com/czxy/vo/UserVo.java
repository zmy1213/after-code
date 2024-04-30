package com.czxy.vo;

import lombok.Data;

@Data
public class UserVo {
    private Integer roleId;
    private String name;
    private Integer uid;
    private Integer page;
    private Integer pageSize;
}
