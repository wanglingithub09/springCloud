package com.test.designpattern.adapter.objectadapter;
/**
* @Author: WangLin
* @Description: 对象适配器：解决类适配器的兼容性问题
* @Date: 2019/1/17 15:49
*/
public class Test {
    public static void main(String[] args) {
        Targetable targetable = new Wrapper(new Source());
        targetable.method1();
        targetable.method2();
    }
}
