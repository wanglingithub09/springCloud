package com.test.designpattern.adapter.classadapter;
/**
* @Author: WangLin
* @Description: 类适配器，核心思想就是：有一个Source类，拥有一个方法，待适配，目标接口是Targetable，
 *                 通过Adapter类，将Source的功能扩展到Targetable里
* @Date: 2019/1/17 15:36
*/
public class Test {
    public static void main(String[] args) {
        Tragetable tragetable = new Adapter();
        tragetable.method1();
        tragetable.method2();
    }
}
