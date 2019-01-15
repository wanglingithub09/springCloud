package com.org.controller;

import com.alibaba.fastjson.JSON;
import com.org.base.vo.ServerData;
import com.org.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
* @Author: WangLin
* @Description: 
* @Date: 2018/11/13 14:36
*/
@RestController
@RequestMapping("/user")
public class UserController extends BasicController{

    @GetMapping("/getUserById/{id}")
    public ServerData<User> getUserById(@PathVariable("id") Integer id){
        return new ServerData<>(new User(id,"李四",20,null));
    }

    @GetMapping("/getUserName")
    public ServerData<String> getUserName(){
        return new ServerData<>("张三");
    }

    @PostMapping(value = "/getUser",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerData<User> getUser(@RequestBody User user, String age){
        System.out.println(JSON.toJSON(user));
        user.setAge(Integer.valueOf(age));
        user.setName("李四");
        user.setPhone(request.getHeader("phone"));
        return new ServerData<>(user);
    }

    @PostMapping(value = "/getUserList",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerData<List<User>> getUserList(@RequestBody User user, String age){
        System.out.println(JSON.toJSON(user));
        user.setAge(Integer.valueOf(age));
        user.setName("李四");
        user.setPhone(request.getHeader("phone"));
        List<User> list = new ArrayList<>();
        list.add(user);
        user = new User(4,"丙丁",55,"89878xxx");
        list.add(user);
        return new ServerData<>(list);
    }
}
