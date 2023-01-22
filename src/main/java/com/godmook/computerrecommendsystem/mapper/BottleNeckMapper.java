package com.godmook.computerrecommendsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BottleNeckMapper {
    @Select("SELECT bottleneck FROM bottleneck WHERE cpu_id=#{id1} AND gpu_id=#{id2}")
    String getBottleneck(@Param("id1")int id1, @Param("id2")int id2);
}
