package com.org.service.impl;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: WangLin
 * @Description: 动态获取服务端口
 * @Date: 2018/11/13 10:14
 */
@Component
public class ServiceInfoImpl implements ApplicationListener<WebServerInitializedEvent> {

    //服务端口
    private int serverPort;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

    public int getPort() {
        return this.serverPort;
    }

}
