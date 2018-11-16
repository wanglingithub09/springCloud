package com.org.controller;

import com.org.base.vo.ServerData;
import com.org.entity.UserDevice;
import com.org.service.UserDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserDeviceController {

    @Autowired
    private UserDeviceService userDeviceService;

    @GetMapping("/getUserDeviceById/{id}")
    public ServerData<UserDevice> getUserDeviceById(@PathVariable("id") Long id){
        return new ServerData<>(userDeviceService.getUserDeviceById(id));
    }


}
