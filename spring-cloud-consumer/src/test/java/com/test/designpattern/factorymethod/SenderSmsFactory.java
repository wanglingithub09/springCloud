package com.test.designpattern.factorymethod;

public class SenderSmsFactory implements Provider {
    @Override
    public Sender procude() {
        return new SmsSender();
    }
}
