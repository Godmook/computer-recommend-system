package com.godmook.computerrecommendsystem.Controller;
import com.godmook.computerrecommendsystem.Model.MainBoardModel;
import com.godmook.computerrecommendsystem.mapper.MainBoardMapper;
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
@RequestMapping("/mainboard")
public class MainBoardController {
    private MainBoardMapper mapper;
    public MainBoardController(MainBoardMapper mapper){this.mapper=mapper;}
    Integer updateHits(int id){return mapper.getHits(id);}
    @ApiOperation(value="MainBard id",notes="id에 해당하는 MainBoard 가지고오기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/model/{id}")
    public MainBoardModel getMainBoardID(@PathVariable("id")int id){
        updateHits(id);
        return mapper.getMainBoardId(id);
    }


    @ApiOperation(value="메인보드 가성비 순위",notes="전체 가성비 순위")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/model/best/{section}")
    public List<Integer> getAllBest(@PathVariable("section")String section){return mapper.getSectionRank(section);}
    @GetMapping("/model/section")
    public List<String> getAllSection(){return mapper.getAllSection();}
    @GetMapping("/model/match/{socket}/{memory}")
    public List<MainBoardModel> getMatchModel(@PathVariable("socket")String socket, @PathVariable("memory")String memory){return mapper.getMatchMainBoard(socket,memory);}

    @GetMapping("/hits/{number}")
    public Integer getHitsBest(@PathVariable("number")int number){return mapper.getHitsMainboard(number-1);}


    @ApiOperation(value="메인보드 교체 제시",notes="CPU의 Socket 을 넣고, RAM의 Memory 를 넣으면 됨. 없는 곳은 빈 문자열 보내면 됨.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/replacable/{socket}/{memory}")
    List<Integer> getMBReplace(@PathVariable("socket")String socket, @PathVariable("memory")String memory){
        if(socket.equals("a") && memory.equals("a")){
            return mapper.getZeroReplace();
        }
        else if(socket.equals("a")){
            return mapper.getTwoReplace(memory);
        }
        else if(memory.equals("a")){
            return mapper.getOneReplace(socket);
        }
        else{
            return mapper.getTotalReplace(socket,memory);
        }
    }

    @GetMapping("/rank/all")
    List<Integer> getRankMB(){
        return mapper.getZeroReplace();
    }
}
