package com.czxy.teamMoney.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class TeamMoney implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;


    @TableField(exist = false)
    private String userName;


    /**
     * 社团id
     */
    private Integer sid;


    @TableField(exist = false)
    private String teamName;

    /**
     * 团费价格
     */
    private Integer price;

    /**
     * 状态（1未交，0已缴）
     */
    private String state;


}
