package com.test.designpattern.observer;

import cn.hutool.core.lang.Console;

public class ObserverSub2 implements Observer {
    @Override
    public void update() {
        Console.log("这是ObserverSub2 update方法");
    }
}
