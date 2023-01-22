package com.godmook.computerrecommendsystem.mapper;

import com.godmook.computerrecommendsystem.Model.RAMModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface RAMMapper {
    @Select("SELECT * FROM ram WHERE id=#{id}")
    RAMModel getRAMID(@Param("id")int id);

    @Select("SELECT id FROM ram ORDER BY rank ASC")
    List<Integer> getAllGoodPrice();

    @Select("SELECT id FROM ram ORDER BY rank ASC LIMIT #{start},#{end}")
    List<Integer> getGoodPrice(@Param("start")int start, @Param("end")int end);

    @Update("UPDATE ram SET hits=(hits+1) WHERE id=#{id}")
    Integer getHits(@Param("id")int id);

    @Select("SELECT id FROM ram WHERE section=#{section} ORDER BY rank ASC")
    List<Integer>  ramReplace(@Param("section")String seciton);

    @Select("SELECT id FROM ram ORDER BY rank ASC")
    List<Integer> rams();
}
