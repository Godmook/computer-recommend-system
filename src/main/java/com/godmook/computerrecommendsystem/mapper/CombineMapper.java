package com.godmook.computerrecommendsystem.mapper;

import com.godmook.computerrecommendsystem.Model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface CombineMapper {
    @Select("SELECT id, price,socket FROM cpu_small WHERE price<=#{price} AND socket LIKE CONCAT('%',#{name},'%') ORDER BY rank ASC")
    List<CPUCombineModel> getCombineCPU(@Param("price")int price,@Param("name")String name);
    @Select("SELECT name,shop_link,image,price FROM cpu WHERE id=#{id}")
    CombineWordModel getCPUModel(@Param("id")int id);
    @Select("SELECT name,shop_link,image,price FROM gpu WHERE id=#{id}")
    CombineWordModel getGPUModel(@Param("id")int id);
    @Select("SELECT name,shop_link,image,price FROM mainboard WHERE id=#{id}")
    CombineWordModel getMBModel(@Param("id")int id);
    @Select("SELECT name,shop_link,image,price FROM ram WHERE id=#{id}")
    CombineWordModel getRAMModel(@Param("id")int id);
    @Select("SELECT name,shop_link,image,price FROM ssd WHERE id=#{id}")
    CombineWordModel getSSDModel(@Param("id")int id);
    @Select("SELECT name,shop_link,image,price FROM hdd WHERE id=#{id}")
    CombineWordModel getHDDModel(@Param("id")int id);
    @Select("SELECT name,shop_link,image,price FROM cooler WHERE id=#{id}")
    CombineWordModel getCoolerModel(@Param("id")int id);
    @Select("SELECT name,shop_link,image,price FROM `case` WHERE id=#{id}")
    CombineWordModel getCaseModel(@Param("id")int id);
    @Select("SELECT name,shop_link,image,price FROM power WHERE id=#{id}")
    CombineWordModel getPowerModel(@Param("id")int id);
    @Select("SELECT id,price,power FROM gpu_small WHERE price<=#{price} ORDER BY rank ASC")
    List<GPUCombineModel> getCombineGPU(@Param("price")int price);
    @Select("SELECT id,price,socket,memory FROM mainboard_small WHERE price<=#{price} AND socket LIKE CONCAT('%',#{name},'%') ORDER BY rank ASC")
    List<MainBoardCombineModel> getCombineMainBoard(@Param("price")int price,@Param("name")String name);
    @Select("SELECT id,price,section FROM ram_small WHERE price<=#{price} AND section LIKE '%DDR4%' ORDER BY rank ASC")
    List<RamCombineModel> getCombineRam(@Param("price")int price);
    @Select("SELECT id,price FROM ssd_small WHERE price<=#{price} ORDER BY rank ASC")
    List<DefaultCombineModel> getCombineSSD(@Param("price")int price);
    @Select("SELECT id,price FROM hdd_small WHERE price<=#{price} ORDER BY rank ASC")
    List<DefaultCombineModel> getCombineHDD(@Param("price")int price);
    @Select("SELECT id,price FROM cooler_small WHERE price<=#{price} ORDER BY rank ASC")
    List<DefaultCombineModel> getCombineCooler(@Param("price")int price);
    @Select("SELECT id,price FROM `case_small` WHERE price<=#{price} ORDER BY rank ASC")
    List<DefaultCombineModel> getCombineCase(@Param("price")int price);
    @Select("SELECT id,price,power FROM power_small WHERE price<=#{price} ORDER BY rank ASC")
    List<GPUCombineModel> getCombinePower(@Param("price")int price);
    @Select("SELECT id FROM power WHERE power>=#{power} ORDER BY rank ASC")
    List<Integer> getLimitPower(@Param("power")int power);
    @Select("SELECT power FROM gpu WHERE id=#{id}")
    Integer getGpuPower(@Param("id")int id);

    @Select("SELECT bottleneck FROM bottleneck WHERE cpu_id=#{id1} AND gpu_id=#{id2} LIMIT 1")
    String getBottleneck(@Param("id1")int id1, @Param("id2")int id2);

    @Select("SELECT DISTINCT COUNT(user_id) FROM combine")
    Integer getUserCount();
    @Select("SELECT COUNT(*) FROM combine")
    Integer getTotalCount();

    @Select("SELECT performance FROM gpu_small WHERE rank=#{id}")
    Integer getGPUPrice(@Param("id")int id);
    @Select("SELECT rank FROM gpu WHERE id=#{id}")
    Integer getGPURank(@Param("id")int id);

    @Insert("INSERT INTO client VALUES(#{cpu_id},#{cpu_count},#{gpu_id},#{gpu_count},#{mainboard_id},#{mainboard_count},#{ram_id},#{ram_count},#{ssd_id},#{ssd_count},#{hdd_id},#{hdd_count},#{case_id},#{case_count},#{cooler_id},#{cooler_count},#{power_id},#{power_count},#{user_id},#{time})")
    Integer insertData(@Param("cpu_id")int cpu_id,
                       @Param("cpu_count")int cpu_count,
                       @Param("gpu_id")int gpu_id,
                       @Param("gpu_count")int gpu_count,
                       @Param("mainboard_id")int mainboard_id,
                       @Param("mainboard_count")int mainboard_count,
                       @Param("ram_id")int ram_id,
                       @Param("ram_count")int ram_count,
                       @Param("ssd_id")int ssd_id,
                       @Param("ssd_count")int ssd_count,
                       @Param("hdd_id")int hdd_id,
                       @Param("hdd_count") int hdd_count,
                       @Param("case_id")int case_id,
                       @Param("case_count")int case_count,
                       @Param("cooler_id")int cooler_id,
                       @Param("cooler_count")int cooler_count,
                       @Param("power_id")int power_id,
                       @Param("power_count")int power_count,
                       @Param("user_id")String user_id,
                       @Param("time")int time);

    @Select("SELECT * FROM ai_combine WHERE price>=#{start} AND price<=#{end} AND socket=#{socket} ORDER BY score DESC LIMIT #{exp},#{exp2}")
    List<CombineAIModel> getSpecificCombine(@Param("start") int start, @Param("end") int end, @Param("socket") String socket,@Param("exp")int exp,@Param("exp2")int exp2);
    @Select("SELECT * FROM ai_combine WHERE price>=#{start} AND price<=#{end} ORDER BY score DESC LIMIT #{exp},#{exp2}")
    List<CombineAIModel> getAllCombine(@Param("start") int start, @Param("end") int end,@Param("exp")int exp,@Param("exp2")int exp2);
}


