package com.test.designpattern.state;

import cn.hutool.core.lang.Console;
import lombok.Data;

@Data
public class State {
    private String value;
    public void method1(){
        Console.log("这是在线的方法");
    }
    public void method2(){
        Console.log("这是离线的方法");
    }
}
