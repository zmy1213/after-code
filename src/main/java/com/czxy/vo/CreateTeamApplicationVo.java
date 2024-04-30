package com.czxy.vo;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class CreateTeamApplicationVo {

    Integer uid;
    Integer status;
    Integer add_or_delete;
    String teamname;
    Integer join_price;
    String slogan;
    String type;
    String username;
    String remark;
    String create_time;
    private Integer page;
    private Integer pageSize;
}
