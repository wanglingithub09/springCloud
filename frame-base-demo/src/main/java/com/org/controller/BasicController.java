package com.org.controller;

import com.org.entity.User;
import com.org.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
/**
* @Author: WangLin
* @Description: 基本信息
* @Date: 2018/11/23 15:33
*/
public class BasicController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    protected HttpServletRequest request;

    public User getCurrentUserInfo(){
        //Cookie[] cookie = request.getCookies();
        //System.out.println(JSONObject.toJSONString(cookie));
        //从session里面获取对应的值
        //String myValue = (String) requestAttributes.getAttribute("value",RequestAttributes.SCOPE_SESSION);
        //Object object = redisUtil.get(request.getHeader(HttpConstants.Headers.X_USER_ID));
        Object object = null;
        User user = new User();
        if(null != object){
            return (User)object;
        }
        return user;
    }

}
