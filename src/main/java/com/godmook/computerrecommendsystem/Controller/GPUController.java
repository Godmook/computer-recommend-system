package com.godmook.computerrecommendsystem.Controller;

import com.godmook.computerrecommendsystem.Model.GPUModel;
import com.godmook.computerrecommendsystem.Model.GPURankModel;
import com.godmook.computerrecommendsystem.mapper.GPUMapper;
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
@RequestMapping("/gpu")
public class GPUController {
    private GPUMapper mapper;
    public GPUController(GPUMapper mapper){this.mapper=mapper;}
    Integer updateHits(int id){return mapper.getHits(id);}
    @ApiOperation(value="GPU id",notes="id에 해당하는 gpu 가지고오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/model/{id}")
    public GPUModel getGPUID(@PathVariable("id")int id){
        updateHits(id);
        return mapper.getGPUModel(id);
    }

    @GetMapping("/rank/specific/{rank}")
    public List<Integer> getRank(@PathVariable("rank")int rank){return mapper.getGPURank(rank);}

    @GetMapping("/rank/all")
    public List<GPURankModel> getAllGPURank(){return mapper.getAllGPURank();}

    @GetMapping("/hits/{number}")
    public Integer getHitsBest(@PathVariable("number")int number){return mapper.getHitsGPU(number-1);}

    @ApiOperation(value="교체가능한 GPU 제시",notes="Power의 Power 값을 보내주면 되고, 없으면 0 보내면 됨.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/replacable/{power}")
    List<Integer> getReplacePower(@PathVariable("power")int power){
        if(power==0){
            return mapper.gpus();
        }
        else{
            return mapper.getReplaceGpu(power);
        }
    }
}
