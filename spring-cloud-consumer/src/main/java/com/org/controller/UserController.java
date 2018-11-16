package com.org.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.org.base.enumclass.RetCode;
import com.org.base.vo.ServerData;
import com.org.entity.User;
import com.org.service.RestTemplateService;
import com.org.util.HttpConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplateService restTemplateServiceImpl;

    @GetMapping("/getUserById/{id}")
    public ServerData<User> getUserById(@PathVariable("id") Long id){
        String result = restTemplateServiceImpl.get("http://spring-cloud-provider/user/getUserById/"+id,null);
        return JSONObject.parseObject(result,ServerData.class);
    }

    @GetMapping("/getEunm")
    public ServerData<String> getEunm(){
        return new ServerData(RetCode.BUSINESS_CODE.getMsgCode());
    }

    @GetMapping("/getUser/{id}")
    public ServerData<User> getUser(@PathVariable("id") Long id){
        Map<String, Object> params = new HashMap<>();
        params.put("name", "王五");
        params.put("id", id);
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("phone", "1234567");
        headerMap.put(HttpConstants.Headers.X_TOKEN_VALUE,HttpConstants.Headers.X_TOKEN_VALUE);
        Map<String,Object> other = new HashMap<>();
        other.put("age","18");
        String url = "http://spring-cloud-provider/user/getUser?age={age}";
        String result = restTemplateServiceImpl.post(url, JSON.toJSONString(params),headerMap,other);
        return JSONObject.parseObject(result,ServerData.class);
    }

    @GetMapping("/getUserList/{id}")
    public ServerData<User> getUserList(@PathVariable("id") Long id){
        Map<String, Object> params = new HashMap<>();
        params.put("name", "王五");
        params.put("id", id);
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("phone", "1234567");
        headerMap.put(HttpConstants.Headers.X_TOKEN_VALUE,HttpConstants.Headers.X_TOKEN_VALUE);
        Map<String,Object> other = new HashMap<>();
        other.put("age","19");
        String url = "http://spring-cloud-provider/user/getUserList?age={age}";
        String result = restTemplateServiceImpl.post(url, JSON.toJSONString(params),headerMap,other);
        return JSONObject.parseObject(result,ServerData.class);
    }


}
