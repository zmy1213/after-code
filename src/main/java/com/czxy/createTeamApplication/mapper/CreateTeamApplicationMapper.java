package com.czxy.createTeamApplication.mapper;

import com.czxy.createTeamApplication.pojo.CreateTeamApplication;
import com.czxy.userTeam.pojo.UserTeam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author
 * @since 2024-03-01
 */
@Mapper
public interface CreateTeamApplicationMapper extends BaseMapper<CreateTeamApplication> {
}
