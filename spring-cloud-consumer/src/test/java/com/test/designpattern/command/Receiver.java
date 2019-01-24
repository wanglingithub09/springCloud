package com.test.designpattern.command;

import cn.hutool.core.lang.Console;

public class Receiver {
    void action(){
        Console.log("command received!");
    }
}
