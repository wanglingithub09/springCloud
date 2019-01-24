package com.test.designpattern.simplefactorymodel.statics;



public class Test {
    public static void main(String[] args) {
        Sender sender = SenderFacotry.produceMail();
        sender.send();
        sender = SenderFacotry.produceSms();
        sender.send();
    }
}
