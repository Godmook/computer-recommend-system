package com.godmook.computerrecommendsystem.mapper;

import com.godmook.computerrecommendsystem.Model.CPUModel;
import com.godmook.computerrecommendsystem.Model.RankModel;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface CPUMapper {
    @Select("SELECT * FROM cpu WHERE id=#{id}")
    CPUModel getCPUDetail (@Param("id") int id);
    @Select("SELECT id FROM cpu ORDER BY rank ASC LIMIT #{start},#{end}")
    List<Integer> getGoodPrice(@Param("start")int start, @Param("end")int end);

    @Update("UPDATE cpu SET hits=(hits+1) WHERE id=#{id}")
    Integer getHits(@Param("id")int id);

    @Select("SELECT id FROM cpu ORDER BY rank ASC")
    List<Integer> getAllGoodPrice();
    @Select("SELECT * FROM cpu WHERE socket=#{socket}")
    List<CPUModel> getSameSocket(@Param("socket") String socket);

    @Select("SELECT id FROM cpu ORDER BY hits DESC LIMIT #{number},1")
    Integer getHitsCPU(@Param("number")int number);


    @Select("SELECT id FROM cpu WHERE socket=#{socket} ORDER BY rank ASC")
    List<Integer> getReplaceCPU(@Param("socket")String socket);

    @Select("SELECT id FROM cpu ORDER BY rank ASC")
    List<Integer> cpus();

}
