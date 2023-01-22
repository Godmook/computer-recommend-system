package com.godmook.computerrecommendsystem.Controller;

import com.godmook.computerrecommendsystem.Model.DefaultModel;
import com.godmook.computerrecommendsystem.mapper.CaseMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RestController
@RequestMapping("/case")
public class CaseController {
    private CaseMapper mapper;
    public CaseController(CaseMapper mapper){this.mapper=mapper;}
    Integer updateHits(int id){return mapper.getHits(id);}
    @GetMapping("/model/{id}")
    DefaultModel getCaseID(@PathVariable("id")int id){
        updateHits(id);
        return mapper.getCaseID(id);
    }

    @GetMapping("/rank/all")
    List<Integer> getRankAll(){return mapper.getAllGoodPrice();}

    @GetMapping("/rank/{start}/{end}")
    public List<Integer> getBestCase(@PathVariable("start")int id1, @PathVariable("end")int id2){return mapper.getGoodPrice(id1-1,id2);}

    @GetMapping("/replacable")
    List<Integer> getReplaceable(){return mapper.caseReplace();}
}
