package com.czxy.applySociety.mapper;


import com.czxy.applySociety.pojo.applySociety;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PathVariable;

@Mapper
public interface ApplySocietyMapper extends BaseMapper<applySociety>{
    @Update("UPDATE apply_society SET statue = #{value} where uid = #{uid} AND sid = #{sid}")
    boolean updateByUidAndSid(@Param("uid") Integer uid, @Param("sid") Integer sid, @Param("value") Integer value);
    @Delete("DELETE FROM apply_society WHERE uid = #{uid} AND sid = #{sid}")
    boolean deleteByUidAndSid(@Param("uid") Integer uid, @Param("sid") Integer sid);
}
