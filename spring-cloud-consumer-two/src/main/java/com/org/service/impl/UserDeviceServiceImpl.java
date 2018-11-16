package com.org.service.impl;

import com.org.entity.UserDevice;
import com.org.mapper.UserDeviceMapper;
import com.org.service.UserDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @Author: WangLin
* @Description: 
* @Date: 2018/11/15 17:10
*/
@Service
public class UserDeviceServiceImpl implements UserDeviceService{

    @Autowired
    private UserDeviceMapper userDeviceMapper;

    @Override
    public UserDevice getUserDeviceById(Long id) {
        return userDeviceMapper.getUserDeviceById(id);
    }
}
