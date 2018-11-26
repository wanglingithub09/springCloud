package com.org.base;

import com.org.constants.HttpConstants;
import com.org.entity.User;
import com.org.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
/**
* @Author: WangLin
* @Description: 基本信息
* @Date: 2018/11/23 15:33
*/
public class BaseInfo {

    @Autowired
    private RedisUtil redisUtil;

    public HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes =(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request;
    }

    public User getCurrentUserInfo(){
        //Cookie[] cookie = request.getCookies();
        //System.out.println(JSONObject.toJSONString(cookie));
        //从session里面获取对应的值
        //String myValue = (String) requestAttributes.getAttribute("value",RequestAttributes.SCOPE_SESSION);
        Object object = redisUtil.get(getRequest().getHeader(HttpConstants.Headers.X_USER_ID));
        User user = new User();
        if(null != object){
            return (User)object;
        }
        return user;
    }

}
