package com.godmook.computerrecommendsystem.mapper;

import com.godmook.computerrecommendsystem.Model.GPUModel;
import com.godmook.computerrecommendsystem.Model.GPURankModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GPUMapper {
    @Select("SELECT * FROM gpu WHERE id=#{id}")
    GPUModel getGPUModel(@Param("id")int id);
    @Select("SELECT id FROM gpu WHERE rank=#{rank} ORDER BY total_rank ASC ")
    List<Integer> getGPURank(@Param("rank")int rank);
    @Select("SELECT * FROM gpu_rank")
    List<GPURankModel> getAllGPURank();

    @Update("UPDATE gpu SET hits=(hits+1) WHERE id=#{id}")
    Integer getHits(@Param("id")int id);

    @Select("SELECT id FROM gpu ORDER BY hits DESC LIMIT #{number},1")
    Integer getHitsGPU(@Param("number")int number);

    @Select("SELECT id FROM gpu WHERE power<=#{power} ORDER BY price ASC")
    List<Integer> getReplaceGpu(@Param("power")int power);
    @Select("SELECT id FROM gpu ORDER BY price ASC")
    List<Integer>  gpus();
}
