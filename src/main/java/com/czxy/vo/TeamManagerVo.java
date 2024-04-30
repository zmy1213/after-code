package com.czxy.vo;


import lombok.Data;

@Data
public class TeamManagerVo {
    private Integer Id;
    private Integer uid;
    private Integer sid;
    private String username;
    private String teamname;
    private Integer page;
    private Integer pageSize;
}
