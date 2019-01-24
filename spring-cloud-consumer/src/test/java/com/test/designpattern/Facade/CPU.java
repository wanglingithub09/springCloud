package com.test.designpattern.Facade;

import cn.hutool.core.lang.Console;

public class CPU {
    void startup(){
        Console.log("CPU startup");
    }
    void shutdown(){
        Console.log("CPU shutdown");
    }
}
