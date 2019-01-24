package com.test.designpattern.proxy;

import cn.hutool.core.lang.Console;

public class Proxy implements Sourceable {

    private Source source;

    public Proxy(){
        super();
        this.source = new Source();
    }

    @Override
    public void method() {
        this.before();
        source.method();
        this.after();
    }

    private void before(){
        Console.log("before proxy");
    }

    private void after(){
        Console.log("after proxy");
    }
}
