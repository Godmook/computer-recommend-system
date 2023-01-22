package com.godmook.computerrecommendsystem.Controller;

import com.godmook.computerrecommendsystem.Model.RAMModel;
import com.godmook.computerrecommendsystem.mapper.RAMMapper;
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
@RequestMapping("/ram")
public class RAMController {
    RAMMapper mapper;
    public RAMController(RAMMapper mapper){this.mapper=mapper;}
    Integer updateHits(int id){return mapper.getHits(id);}
    @GetMapping("/model/{id}")
    RAMModel getSSDID(@PathVariable("id")int id){
        updateHits(id);
        return mapper.getRAMID(id);
    }

    @GetMapping("/rank/all")
    List<Integer> getRankAll(){return mapper.getAllGoodPrice();}

    @GetMapping("/rank/{start}/{end}")
    public List<Integer> getBestSSD(@PathVariable("start")int id1, @PathVariable("end")int id2){return mapper.getGoodPrice(id1-1,id2);}


    @ApiOperation(value="교체가능한 램 제시",notes="Mainboard에 적합하는 Memory 를 보내주면 되고, 없으면 빈 문자열 보내면 됨")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/replacable/{memory}")
    List<Integer> getRAMReplace(@PathVariable("memory")String memory){
        if(memory.equals("a")){
            return mapper.rams();
        }
        else{
            return mapper.ramReplace(memory);
        }
    }
}
