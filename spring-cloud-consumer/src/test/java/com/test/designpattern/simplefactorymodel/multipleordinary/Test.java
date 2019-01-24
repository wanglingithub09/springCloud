package com.test.designpattern.simplefactorymodel.multipleordinary;
/**
* @Author: WangLin
* @Description: Test多个方法简单工厂模式
* @Date: 2019/1/16 11:40
*/
public class Test {
    public static void main(String[] args) {
        SenderFactory factory = new SenderFactory();
        Sender sender = factory.produceMail();
        sender.send();
        sender = factory.produceSms();
        sender.send();
    }
}
