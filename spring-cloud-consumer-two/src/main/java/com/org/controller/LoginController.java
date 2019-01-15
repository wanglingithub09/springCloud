package com.org.controller;

import com.org.base.annotation.SkipVerification;
import com.org.base.enumclass.RetCode;
import com.org.base.vo.ServerData;
import com.org.entity.User;
import com.org.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
* @Author: WangLin
* @Description: 登录认证
* @Date: 2018/11/23 16:03
*/
@RestController
@RequestMapping("/login")
public class LoginController extends BasicController{

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录跳过验证
     * @param user
     * @return
     */
    @SkipVerification
    @RequestMapping(value = "/loginAuth",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerData<User> loginAuth(@RequestBody User user){
        ServerData serverData = new ServerData<>();
        Object obj = redisUtil.get(String.valueOf(user.getId()));
        if(null != obj){
            user = (User)obj;
            serverData.setBo(user);
            return serverData;
        }
        serverData.setCode(RetCode.VALIDATIONEXCEPTION_CODE.getMsgCode());
        serverData.setMsg("身份认证错误，请检查。");
        return serverData;
    }

    @SkipVerification
    @RequestMapping(value = "/addUser",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerData<User> addUser(){
        User user = new User();
        user.setId(12345);
        user.setPhone("133xxxx3124");
        user.setPassword("456789");
        user.setAge(30);
        user.setName("WangLin");
        redisUtil.set("12345",user);
        return new ServerData<>(user);
    }
}
