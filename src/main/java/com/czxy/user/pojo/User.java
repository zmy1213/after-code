package com.czxy.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 昵称
     */
    private String username;

    /**
     * 名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private String sex;


    /**
     * 角色
     */
    private String roleId;

    /**
     * 手机号
     */
    private String tel;

    private String classname;

}
