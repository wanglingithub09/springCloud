package com.test.designpattern.factorymethod;

import cn.hutool.core.lang.Console;

public class MailSender implements Sender {
    @Override
    public void send() {
        Console.log("这是mail发送");
    }
}
