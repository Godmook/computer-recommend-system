package com.godmook.computerrecommendsystem.Controller;

import com.godmook.computerrecommendsystem.Model.DefaultModel;
import com.godmook.computerrecommendsystem.mapper.AnotherMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnotherController {
    private AnotherMapper mapper;
    public AnotherController(AnotherMapper mapper){this.mapper=mapper;}

    @GetMapping("/keyboard/model/{id}")
    DefaultModel getKeyboardID(@PathVariable("id")int id){return mapper.getKeyboardID(id);}

    @GetMapping("/monitor/model/{id}")
    DefaultModel getMonitorID(@PathVariable("id")int id){return mapper.getMonitorID(id);}

    @GetMapping("/mouse/model/{id}")
    DefaultModel getMouseID(@PathVariable("id")int id){return mapper.getMouseID(id);}

    @GetMapping("/keyboard/replacable")
    List<Integer> getKeyboardReplace(){return mapper.getKeyboardReplace();}

    @GetMapping("/monitor/replacable")
    List<Integer> getMonitorReplace(){return mapper.getMonitorReplace();}

    @GetMapping("/mouse/replacable")
    List<Integer> getMouseReplace(){return mapper.getMouseReplace();}

    @GetMapping("/keyboard/rank/all")
    List<Integer> getKeyboardRank(){return mapper.getKeyboardReplace();}

    @GetMapping("/monitor/rank/all")
    List<Integer> getMonitorRank(){return mapper.getMonitorReplace();}

    @GetMapping("/mouse/rank/all")
    List<Integer> getMouseRank(){return mapper.getMouseReplace();}
}
