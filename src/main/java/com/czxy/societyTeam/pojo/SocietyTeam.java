package com.czxy.societyTeam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

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
public class SocietyTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 社团id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 社团名称
     */
    private String name;

    /**
     * 社团类型
     */
    private String type;

    /**
     * 社团口号
     */
    private String slogan;

    /**
     * 入团团费
     */
    private Integer joinPrice;

    /**
     * 社团简介
     */
    private String remark;

    /**
     * 社团创建时期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

}
