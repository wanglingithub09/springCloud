package com.test.designpattern.adapter.classadapter;

import cn.hutool.core.lang.Console;

public class Adapter extends Source implements Tragetable {
    @Override
    public void method2() {
        Console.log("这是Adapter method2方法");
    }
}
