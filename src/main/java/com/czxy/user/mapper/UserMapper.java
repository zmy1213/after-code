package com.czxy.user.mapper;

import com.czxy.user.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czxy.userTeam.pojo.UserTeam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Update("UPDATE user SET role_id = #{value} WHERE id = #{id}")
    boolean updataByid(@Param("id") Integer id,@Param("value") Integer value);
}
