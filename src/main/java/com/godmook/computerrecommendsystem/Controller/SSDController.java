package com.godmook.computerrecommendsystem.Controller;

import com.godmook.computerrecommendsystem.Model.DefaultModel;
import com.godmook.computerrecommendsystem.Model.RankModel;
import com.godmook.computerrecommendsystem.mapper.SSDMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RestController
@RequestMapping("/ssd")
public class SSDController {
    private SSDMapper mapper;
    public SSDController(SSDMapper mapper){this.mapper=mapper;}
    Integer updateHits(int id){return mapper.getHits(id);}
    @GetMapping("/model/{id}")
    DefaultModel getSSDID(@PathVariable("id")int id){
        updateHits(id);
        return mapper.getSSDID(id);
    }

    @GetMapping("/rank/all")
    List<RankModel> getRankAll(){return mapper.getAllGoodPrice();}

    @GetMapping("/rank/{start}/{end}")
    public List<Integer> getBestSSD(@PathVariable("start")int id1, @PathVariable("end")int id2){return mapper.getGoodPrice(id1-1,id2);}


    @ApiOperation(value="교체가능한 SSD 제시",notes="아무 SSD 든 일단 다 가지고 옴")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/replacable")
    List<Integer> getReplaceable(){return mapper.ssdReplace();}
}
