package com.godmook.computerrecommendsystem.mapper;

import com.godmook.computerrecommendsystem.Model.DefaultModel;
import com.godmook.computerrecommendsystem.Model.RankModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface SSDMapper {

    @Select("SELECT * FROM ssd WHERE id=#{id}")
    DefaultModel getSSDID(@Param("id")int id);

    @Select("SELECT name,total_score FROM ssd ORDER BY rank ASC")
    List<RankModel> getAllGoodPrice();

    @Select("SELECT id FROM ssd ORDER BY rank ASC LIMIT #{start},#{end}")
    List<Integer> getGoodPrice(@Param("start")int start, @Param("end")int end);

    @Update("UPDATE ssd SET hits=(hits+1) WHERE id=#{id}")
    Integer getHits(@Param("id")int id);

    @Select("SELECT id FROM ssd ORDER BY rank ASC")
    List<Integer>  ssdReplace();
}
