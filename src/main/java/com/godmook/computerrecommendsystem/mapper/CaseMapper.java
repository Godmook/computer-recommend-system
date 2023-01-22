package com.godmook.computerrecommendsystem.mapper;

import com.godmook.computerrecommendsystem.Model.DefaultModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CaseMapper {
    @Select("SELECT * FROM `case` WHERE id=#{id}")
    DefaultModel getCaseID(@Param("id")int id);

    @Select("SELECT id FROM `case` ORDER BY rank ASC")
    List<Integer> getAllGoodPrice();

    @Select("SELECT id FROM `case` ORDER BY rank ASC LIMIT #{start},#{end}")
    List<Integer> getGoodPrice(@Param("start")int start, @Param("end")int end);

    @Update("UPDATE `case` SET hits=(hits+1) WHERE id=#{id}")
    Integer getHits(@Param("id")int id);

    @Select("SELECT id FROM `case` ORDER BY rank ASC")
    List<Integer>  caseReplace();
}
