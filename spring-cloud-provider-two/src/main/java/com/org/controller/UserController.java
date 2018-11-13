package com.org.controller;

import com.alibaba.fastjson.JSON;
import com.org.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "/getUserById/{id}")
    public User getUser(@PathVariable("id") Integer id){
        return new User(id,"李四",20,null);
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
        return new User(user.getId(),"李四",Integer.valueOf(age),request.getHeader("phone"));
    }
}
