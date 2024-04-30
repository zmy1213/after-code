package com.czxy.vo;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

@Data
public class SocietyTeamVo {

    Integer Id;
    String name;
    Integer join_price;
    String slogan;
    String type;
    String remark;
    String create_time;
}
