package com.godmook.computerrecommendsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT COUNT(*) FROM user WHERE id=#{id} AND password=#{password}")
    Integer checkLogin(@Param("id")String id,@Param("password")String password);
}
