package com.test.designpattern.chainofresponsibility;

import cn.hutool.core.lang.Console;

public class MyHandler extends AbstractHandler implements Handler {

    private String name;

    public MyHandler(String name){
        this.name = name;
    }

    @Override
    public void operator() {
        Console.log("hi: "+name);
        if(getHandler()!=null){
            getHandler().operator();
        }
    }
}
