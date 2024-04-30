package com.czxy.vo;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.PrimitiveIterator;
@Data
public class ApplySocietyVo {
    private Integer Id;
    private Integer uid;
    private Integer sid;

    private String username;
    private String teamname;
    private Integer page;
    private Integer pageSize;
}
