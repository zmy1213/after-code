package com.czxy.teamMoney.mapper;

import com.czxy.teamMoney.pojo.TeamMoney;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface TeamMoneyMapper extends BaseMapper<TeamMoney> {
    @Delete("DELETE FROM team_money WHERE uid = #{uid} AND sid = #{sid}")
    boolean deleteByUidAndSid(@Param("uid") Integer uid, @Param("sid") Integer sid);
}
