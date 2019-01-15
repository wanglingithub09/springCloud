package com.org.controller;

import com.org.base.vo.ServerData;
import com.org.entity.UserDevice;
import com.org.service.UserDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Author: WangLin
* @Description:
* @Date: 2018/11/15 17:33
*/
@RestController
@RequestMapping("/userDevice")
public class UserDeviceController extends BasicController{

    @Autowired
    private UserDeviceService userDeviceService;

    @GetMapping(value="/getUserDeviceById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServerData<UserDevice> getUserDeviceById(@PathVariable("id") Long id){
        System.out.println(request.getHeader("X_USER_ID"));
        return new ServerData<>(userDeviceService.getUserDeviceById(id));
    }


}
