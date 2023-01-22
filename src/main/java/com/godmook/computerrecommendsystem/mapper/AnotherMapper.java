package com.godmook.computerrecommendsystem.mapper;

import com.godmook.computerrecommendsystem.Model.DefaultModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface AnotherMapper {
    @Select("SELECT * FROM `keyboard` WHERE id=#{id}")
    DefaultModel getKeyboardID(@Param("id")int id);
    @Select("SELECT * FROM monitor WHERE id=#{id}")
    DefaultModel getMonitorID(@Param("id")int id);
    @Select("SELECT * FROM `mouse` WHERE id=#{id}")
    DefaultModel getMouseID(@Param("id")int id);

    @Select("SELECT id FROM keyboard")
    List<Integer> getKeyboardReplace();

    @Select("SELECT id FROM monitor")
    List<Integer> getMonitorReplace();

    @Select("SELECT id FROM mouse")
    List<Integer> getMouseReplace();
}
