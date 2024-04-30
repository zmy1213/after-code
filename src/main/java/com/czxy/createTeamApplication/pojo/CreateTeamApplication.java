package com.czxy.createTeamApplication.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2024-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateTeamApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer uid;

    private Integer status;

    private Integer addOrDelete;

    private String teamname;

    private String username;

    private String type;

    private String slogan;

    private Integer joinPrice;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

}
