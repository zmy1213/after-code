package com.czxy.userTeam.mapper;

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
public interface UserTeamMapper extends BaseMapper<UserTeam> {
    @Select("select sid from user_team where uid = #{uid}")
    List<Integer> findSidByUid(@Param("uid") Integer uid);

    @Select("select * from user_team where uid = #{uid} And sid = #{sid}")
    List<UserTeam> findSidByUidAndUid(@Param("uid") Integer uid,@Param("sid") Integer sid);
    @Delete("DELETE FROM user_team WHERE uid = #{uid} AND sid = #{sid}")
    boolean deleteByUidAndSid(@Param("uid") Integer uid,@Param("sid") Integer sid);
}
