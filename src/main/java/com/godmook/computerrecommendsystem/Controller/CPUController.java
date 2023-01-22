package com.godmook.computerrecommendsystem.Controller;

import com.godmook.computerrecommendsystem.Model.CPUModel;
import com.godmook.computerrecommendsystem.Model.RankModel;
import com.godmook.computerrecommendsystem.mapper.CPUMapper;
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
@RequestMapping("/cpu")
public class CPUController {
    private CPUMapper mapper;
    public CPUController(CPUMapper mapper){this.mapper=mapper;}
    @ApiOperation(value="CPU 모델",notes="CPU 모델 정보 가지고 오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    Integer updateHits(int id){return mapper.getHits(id);}
    @GetMapping("/model/{id}")
    public CPUModel getCPUModel(@PathVariable("id")int id){
        updateHits(id);
        return mapper.getCPUDetail(id);
    }

    @ApiOperation(value="CPU 같은 소켓",notes="CPU 소켓 같은거 가지고 오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/model/socket/{socket}")
    public List<CPUModel> getCPUModel(@PathVariable("socket")String socket){
        return mapper.getSameSocket(socket);
    }

    @ApiOperation(value="CPU 가성비 순위",notes="start= 시작등수, end= 끝등수")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/model/best/{start}/{end}")
    public List<Integer> getBestCPU(@PathVariable("start")int id1, @PathVariable("end")int id2){return mapper.getGoodPrice(id1-1,id2-id1+1);}

    @ApiOperation(value="CPU 가성비 순위",notes="전체 가성비 순위")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/rank/all")
    public List<Integer> getAllBest(){return mapper.getAllGoodPrice();}
    @GetMapping("/hits/{number}")
    public Integer getHitsBest(@PathVariable("number")int number){return mapper.getHitsCPU(number-1);}


    @ApiOperation(value="교체가능한 CPU 제시",notes="메인보드의 Socket 값을 보내주면 됨, 없으면 빈 문자열 보내면 됨")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/replacable/{socket}")
    List<Integer> getReplacePower(@PathVariable("socket")String socket){
        if(socket.equals("a")){
            return mapper.cpus();
        }
        else{
            return mapper.getReplaceCPU(socket);
        }
    }
}
