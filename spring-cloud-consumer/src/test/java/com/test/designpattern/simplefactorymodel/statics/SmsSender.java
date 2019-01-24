package com.test.designpattern.simplefactorymodel.statics;

import cn.hutool.core.lang.Console;

public class SmsSender implements Sender {
    @Override
    public void send() {
        Console.log("这是SMS发送");
    }
}
