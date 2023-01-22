package com.godmook.computerrecommendsystem.mapper;

import com.godmook.computerrecommendsystem.Model.DefaultModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface HDDMapper {

    @Select("SELECT * FROM hdd WHERE id=#{id}")
    DefaultModel getSSDID(@Param("id")int id);

    @Select("SELECT id FROM hdd ORDER BY rank ASC")
    List<Integer> getAllGoodPrice();

    @Select("SELECT id FROM hdd ORDER BY rank ASC LIMIT #{start},#{end}")
    List<Integer> getGoodPrice(@Param("start")int start, @Param("end")int end);

    @Select("SELECT id FROM hdd ORDER BY rank ASC")
    List<Integer>  hddReplace();
}
