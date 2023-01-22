package com.godmook.computerrecommendsystem.Controller;

import com.godmook.computerrecommendsystem.Model.CPUModel;
import com.godmook.computerrecommendsystem.mapper.BottleNeckMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bottleneck")
public class BottleneckController {
    private BottleNeckMapper mapper;
    public BottleneckController(BottleNeckMapper mapper){this.mapper=mapper;}
    @ApiOperation(value="BottleNeck 반환",notes="앞에는 CPU, 뒤에는 GPU")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/model/{id1}/{id2}")
    public String getCPUModel(@PathVariable("id1")int id1,@PathVariable("id2")int id2){
        if(id2==18302792){
            return mapper.getBottleneck(id1,16986743);
        }
        else if(id2==17982362){
            return mapper.getBottleneck(id1,16986743);
        }
        else if(id2==0)return "100%";
        else
            return mapper.getBottleneck(id1,id2);
    }
}
