package com.test.designpattern.Facade;

import cn.hutool.core.lang.Console;

public class Disk {
    void startup(){
        Console.log("Disk startup");
    }
    void shutdown(){
        Console.log("Disk shutdown");
    }
}
