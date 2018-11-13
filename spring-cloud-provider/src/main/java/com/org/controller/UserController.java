package com.org.controller;

import com.alibaba.fastjson.JSON;
import com.org.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "/getUserById/{id}")
    public User getUser(@PathVariable("id") Integer id){
        return new User(id,"张三",18,null);
    }

    @GetMapping("/getUserName")
    public String getUserName(){
        return "张三";
    }

    @PostMapping(value = "/test/post",produces = MediaType.APPLICATION_JSON_VALUE)
    public User testPost(@RequestBody User user,String age, HttpServletRequest request){
        System.out.println(JSON.toJSON(user));
        System.out.println(request.getParameter("age"));
        System.out.println(request.getHeader("X_AUTH_VALUE"));
        System.out.println(request.getHeader("phone"));
        return new User(user.getId(),user.getName(),Integer.valueOf(age),request.getHeader("phone"));
    }
}
