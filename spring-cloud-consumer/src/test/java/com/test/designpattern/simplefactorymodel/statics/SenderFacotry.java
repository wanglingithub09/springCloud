package com.test.designpattern.simplefactorymodel.statics;

public class SenderFacotry {
    public static Sender produceMail(){
        return new MailSender();
    }
    public static Sender produceSms(){
        return new SmsSender();
    }
}
