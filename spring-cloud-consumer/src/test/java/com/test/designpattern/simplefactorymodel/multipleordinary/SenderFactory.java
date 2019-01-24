package com.test.designpattern.simplefactorymodel.multipleordinary;

public class SenderFactory {
    public Sender produceMail(){
        return new MailSender();
    }
    public Sender produceSms(){
        return new SmsSender();
    }
}
