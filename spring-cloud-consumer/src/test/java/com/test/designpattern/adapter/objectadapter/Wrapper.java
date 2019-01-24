package com.test.designpattern.adapter.objectadapter;


import cn.hutool.core.lang.Console;

public class Wrapper implements Targetable{

    private Source source;

    public Wrapper(Source source){
        this.source = source;
    }

    @Override
    public void method1() {
        if(this.source != null) {
            source.method1();
        }
    }

    @Override
    public void method2() {
        Console.log("这是Wrapper method2方法");
    }
}
