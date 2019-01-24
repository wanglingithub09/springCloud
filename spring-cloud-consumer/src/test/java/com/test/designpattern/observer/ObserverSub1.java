package com.test.designpattern.observer;

import cn.hutool.core.lang.Console;

public class ObserverSub1 implements Observer {
    @Override
    public void update() {
        Console.log("这是ObserverSub1 update方法");
    }
}
