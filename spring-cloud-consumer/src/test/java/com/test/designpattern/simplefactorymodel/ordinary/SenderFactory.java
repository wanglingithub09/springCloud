package com.test.designpattern.simplefactorymodel.ordinary;

public class SenderFactory {
    public Sender produce(String type){
        Sender sender = null;
        if ("email".equals(type)) {
            return new MailSender();
        }else if("sms".equals(type)){
            return new SmsSender();
        }else {
            return sender;
        }
    }
}
