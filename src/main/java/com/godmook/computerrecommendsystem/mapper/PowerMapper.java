package com.godmook.computerrecommendsystem.mapper;

import com.godmook.computerrecommendsystem.Model.PowerModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PowerMapper {
    @Select("SELECT * FROM power WHERE id=#{id}")
    PowerModel getPowerID(@Param("id")int id);

    @Select("SELECT id FROM power ORDER BY rank ASC")
    List<Integer> getAllGoodPrice();

    @Select("SELECT id FROM power ORDER BY rank ASC LIMIT #{start},#{end}")
    List<Integer> getGoodPrice(@Param("start")int start, @Param("end")int end);

    @Update("UPDATE power SET hits=(hits+1) WHERE id=#{id}")
    Integer getHits(@Param("id")int id);

    @Select("SELECT id FROM power WHERE power>=#{power} ORDER BY rank ASC")
    List<Integer> getReplacePower(@Param("power")int power);
    @Select("SELECT id FROM power ORDER BY rank ASC")
    List<Integer> getPowers();
}
