package com.czxy.teamImage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.models.auth.In;
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
public class TeamImage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uid;

    private String path;

    private Integer status;

}
