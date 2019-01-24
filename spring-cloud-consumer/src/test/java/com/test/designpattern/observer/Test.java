package com.test.designpattern.observer;

import cn.hutool.core.lang.Console;
/**
* @Author: WangLin
* @Description: 观察者模式
 * 观察者模式很好理解，类似于邮件订阅和RSS订阅，当我们浏览一些博客或wiki时，
 * 经常会看到RSS图标，就这的意思是，当你订阅了该文章，如果后续有更新，会及时通知你。
 * 其实，简单来讲就一句话：当一个对象变化时，其它依赖该对象的对象都会收到通知，并且随着变化！
* @Date: 2019/1/18 14:27
*/
public class Test {
    public static void main(String[] args) {
        Subject subject = new MySubject();
        ObserverSub1 os1 = new ObserverSub1();
        subject.add(os1);
        ObserverSub1 os2 = new ObserverSub1();
        subject.add(os2);
        subject.operation();
        subject.delete(os2);
        Console.log("----------------");
        subject.operation();
    }
}
