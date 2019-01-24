package com.test.designpattern.factorymethod;

public class SenderMailFactory implements Provider {
    @Override
    public Sender procude() {
        return new MailSender();
    }
}
