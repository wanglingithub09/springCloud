package com.test.designpattern.chainofresponsibility;
/**
* @Author: WangLin
* @Description: 责任链模式
 * 有多个对象，每个对象持有对下一个对象的引用，这样就会形成一条链，
 * 请求在这条链上传递，直到某一对象决定处理该请求。
 * 但是发出者并不清楚到底最终那个对象会处理该请求，所以，责任链模式可以实现，
 * 在隐瞒客户端的情况下，对系统进行动态的调整
* @Date: 2019/1/18 17:12
*/
public class Test {
    public static void main(String[] args) {
        MyHandler myHandlerA = new MyHandler("A");
        MyHandler myHandlerB = new MyHandler("B");
        MyHandler myHandlerC = new MyHandler("C");
        MyHandler myHandlerD = new MyHandler("D");
        myHandlerA.setHandler(myHandlerB);
        myHandlerB.setHandler(myHandlerC);
        myHandlerC.setHandler(myHandlerD);
        myHandlerA.operator();

    }
}