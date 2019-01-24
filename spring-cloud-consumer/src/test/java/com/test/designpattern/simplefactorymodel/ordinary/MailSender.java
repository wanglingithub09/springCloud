package com.test.designpattern.simplefactorymodel.ordinary;

import cn.hutool.core.lang.Console;

public class MailSender implements Sender {
    @Override
    public void send() {
        Console.log("this is mailsender!");
    }
}
