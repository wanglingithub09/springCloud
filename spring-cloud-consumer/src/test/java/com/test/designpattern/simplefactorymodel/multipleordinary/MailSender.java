package com.test.designpattern.simplefactorymodel.multipleordinary;

import cn.hutool.core.lang.Console;

public class MailSender implements Sender {

    @Override
    public void send() {
        Console.log("这是邮件发送!");
    }
}
