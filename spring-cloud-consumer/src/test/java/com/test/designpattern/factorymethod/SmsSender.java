package com.test.designpattern.factorymethod;

import cn.hutool.core.lang.Console;

public class SmsSender implements Sender {
    @Override
    public void send() {
        Console.log("这是SMS发送");
    }
}
