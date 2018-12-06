package com.org.service.impl;

import com.org.service.ServicePort;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: WangLin
 * @Description: 动态获取服务端口
 * @Date: 2018/11/13 10:14
 */
@Component
public class ServicePortImpl implements ApplicationListener<WebServerInitializedEvent>,ServicePort {

    //服务端口
    private int serverPort;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

    @Override
    public int getPort() {
        return this.serverPort;
    }

}
