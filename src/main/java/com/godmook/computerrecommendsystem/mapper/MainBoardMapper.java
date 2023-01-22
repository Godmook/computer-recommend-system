package com.godmook.computerrecommendsystem.mapper;

import com.godmook.computerrecommendsystem.Model.MainBoardModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MainBoardMapper {
    @Select("SELECT * FROM mainboard WHERE id=#{id}")
    MainBoardModel getMainBoardId(@Param("id")int id);


    @Select("SELECT id FROM mainboard WHERE section=#{section} ORDER BY rank ASC")
    List<Integer> getSectionRank(@Param("section")String section);

    @Select("SELECT DISTINCT section FROM mainboard")
    List<String> getAllSection();

    @Select("SELECT * FROM mainboard WHERE socket=#{socket} AND memory=#{memory}")
    List<MainBoardModel> getMatchMainBoard(@Param("socket")String socket, @Param("memory")String memory);

    @Update("UPDATE mainboard SET hits=(hits+1) WHERE id=#{id}")
    Integer getHits(@Param("id")int id);

    @Select("SELECT id FROM mainboard ORDER BY hits DESC LIMIT #{number},1")
    Integer getHitsMainboard(@Param("number")int number);

    @Select("SELECT id FROM mainboard WHERE socket=#{socket} AND memory=#{memory} ORDER BY rank ASC")
    List<Integer> getTotalReplace(@Param("socket")String socket, @Param("memory")String memory);
    @Select("SELECT id FROM mainboard WHERE socket=#{socket} ORDER BY rank ASC")
    List<Integer> getOneReplace(@Param("socket")String socket);
    @Select("SELECT id FROM mainboard WHERE memory=#{memory} ORDER BY rank ASC")
    List<Integer> getTwoReplace(@Param("memory")String memory);
    @Select("SELECT id FROM mainboard ORDER BY rank ASC")
    List<Integer> getZeroReplace();
}
