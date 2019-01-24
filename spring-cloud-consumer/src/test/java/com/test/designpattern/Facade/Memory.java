package com.test.designpattern.Facade;

import cn.hutool.core.lang.Console;

public class Memory {
    void startup(){
        Console.log("Memory startup");
    }
    void shutdown(){
        Console.log("Memory shutdown");
    }
}
