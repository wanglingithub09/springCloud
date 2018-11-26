package com.org.controller;

import com.alibaba.fastjson.JSON;
import com.org.base.BaseInfo;
import com.org.base.vo.ServerData;
import com.org.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseInfo {

    @GetMapping("/getUserById/{id}")
    public ServerData<User> getUser(HttpServletRequest request,@PathVariable("id") Integer id){
        System.out.println(request.getHeader("X_USER_ID"));
        System.out.println(getCurrentUserInfo());
        return new ServerData<>(new User(id,"张三",18,null));
    }

    @GetMapping("/getUserName")
    public ServerData<String> getUserName(){
        return new ServerData<>("张三");
    }

    @PostMapping(value = "/getUser",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerData<User> getUser(@RequestBody User user,String age, HttpServletRequest request){
        System.out.println(getCurrentUserInfo());
        System.out.println(JSON.toJSON(user));
        user.setAge(Integer.valueOf(age));
        user.setPhone(request.getHeader("phone"));
        return new ServerData<>(user);
    }

    @PostMapping(value = "/getUserList",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerData<List<User>> getUserList(@RequestBody User user, String age, HttpServletRequest request){
        System.out.println(JSON.toJSON(user));
        user.setAge(Integer.valueOf(age));
        user.setPhone(request.getHeader("phone"));
        List<User> list = new ArrayList<>();
        list.add(user);
        user = new User(8,"甲乙",90,"22222xxx");
        list.add(user);
        return new ServerData<>(list);
    }
}
