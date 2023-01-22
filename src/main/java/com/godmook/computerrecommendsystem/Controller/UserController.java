package com.godmook.computerrecommendsystem.Controller;

import com.godmook.computerrecommendsystem.mapper.CombineMapper;
import com.godmook.computerrecommendsystem.mapper.UserMapper;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
@RestController
@RequestMapping("/user")
public class UserController {
    private UserMapper mapper;
    public UserController(UserMapper mapper){this.mapper=mapper;}
    @GetMapping("/{id}/{password}")
    Integer isLogin(@PathVariable("id")String id,@PathVariable("password")String password){return mapper.checkLogin(id,password);}
}
