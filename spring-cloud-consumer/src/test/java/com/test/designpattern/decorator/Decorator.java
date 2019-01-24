package com.test.designpattern.decorator;

import cn.hutool.core.lang.Console;

public class Decorator implements Sourceable {

    private Sourceable sourceable;

    public Decorator(Sourceable sourceable){
        super();
        this.sourceable = sourceable;
    }

    @Override
    public void method() {
        Console.log("before decorator");
        sourceable.method();
        Console.log("after decorator");
    }
}
