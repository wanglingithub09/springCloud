package com.test.designpattern.mediator;

import cn.hutool.core.lang.Console;

public class User1 extends User {

    public User1(Mediator mediator) {
        super(mediator);
    }

    @Override
    void work() {
        Console.log("用户1工作");
    }
}
