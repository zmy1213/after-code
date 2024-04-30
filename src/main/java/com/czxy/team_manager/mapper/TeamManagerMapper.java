package com.czxy.team_manager.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czxy.team_manager.pojo.TeamManager;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface TeamManagerMapper extends BaseMapper<TeamManager> {
    @Delete("DELETE FROM team_money WHERE uid = #{uid} AND sid = #{sid}")
    boolean deleteByUidAndSid(@Param("uid") Integer uid, @Param("sid") Integer sid);
}
