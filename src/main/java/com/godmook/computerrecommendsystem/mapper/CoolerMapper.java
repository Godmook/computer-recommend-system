package com.godmook.computerrecommendsystem.mapper;

import com.godmook.computerrecommendsystem.Model.DefaultModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CoolerMapper {

    @Select("SELECT * FROM cooler WHERE id=#{id}")
    DefaultModel getCoolerID(@Param("id")int id);


    @Select("SELECT id FROM cooler ORDER BY rank ASC")
    List<Integer> getAllGoodPrice();

    @Select("SELECT id FROM cooler ORDER BY rank ASC LIMIT #{start},#{end}")
    List<Integer> getGoodPrice(@Param("start")int start, @Param("end")int end);

    @Update("UPDATE cooler SET hits=(hits+1) WHERE id=#{id}")
    Integer getHits(@Param("id")int id);

    @Select("SELECT id FROM cooler ORDER BY rank ASC")
    List<Integer>  coolerReplace();
}
