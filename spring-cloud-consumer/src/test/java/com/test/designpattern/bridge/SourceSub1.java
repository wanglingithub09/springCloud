package com.test.designpattern.bridge;

import cn.hutool.core.lang.Console;

public class SourceSub1 implements Sourceable {
    @Override
    public void method() {
        Console.log("这是SourceSub1 method方法");
    }
}
