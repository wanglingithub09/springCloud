package com.test.designpattern.bridge;

import cn.hutool.core.lang.Console;

public class SourceSub2 implements Sourceable {
    @Override
    public void method() {
        Console.log("这是SourceSub2 method方法");
    }
}
