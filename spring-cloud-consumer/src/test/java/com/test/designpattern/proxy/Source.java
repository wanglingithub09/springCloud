package com.test.designpattern.proxy;

import cn.hutool.core.lang.Console;

public class Source implements Sourceable {
    @Override
    public void method() {
        Console.log("这是Source method方法");
    }
}
