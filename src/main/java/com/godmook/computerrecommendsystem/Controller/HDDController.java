package com.godmook.computerrecommendsystem.Controller;

import com.godmook.computerrecommendsystem.Model.DefaultModel;
import com.godmook.computerrecommendsystem.mapper.HDDMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RestController
@RequestMapping("/hdd")
public class HDDController {
    HDDMapper mapper;
    public HDDController(HDDMapper mapper){this.mapper=mapper;}
    @GetMapping("/model/{id}")
    DefaultModel getSSDID(@PathVariable("id")int id){return mapper.getSSDID(id);}

    @GetMapping("/rank/all")
    List<Integer> getRankAll(){return mapper.getAllGoodPrice();}

    @GetMapping("/rank/{start}/{end}")
    public List<Integer> getBestSSD(@PathVariable("start")int id1, @PathVariable("end")int id2){return mapper.getGoodPrice(id1-1,id2);}

    @GetMapping("/replacable")
    List<Integer> getReplaceable(){return mapper.hddReplace();}
}
