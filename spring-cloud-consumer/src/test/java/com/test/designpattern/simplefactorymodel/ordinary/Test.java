package com.test.designpattern.simplefactorymodel.ordinary;
/**
* @Author: WangLin
* @Description: Test普通工厂模式
* @Date: 2019/1/16 11:33
*/
public class Test {
    public static void main(String[] args) {
        SenderFactory factory = new SenderFactory();
        Sender sender = factory.produce("email");
        sender.send();
    }
}
