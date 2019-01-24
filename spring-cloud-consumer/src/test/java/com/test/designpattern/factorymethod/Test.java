package com.test.designpattern.factorymethod;

public class Test {
    public static void main(String[] args) {
        Sender sender = new SenderMailFactory().procude();
        sender.send();
        sender = new SenderSmsFactory().procude();
        sender.send();
    }
}
