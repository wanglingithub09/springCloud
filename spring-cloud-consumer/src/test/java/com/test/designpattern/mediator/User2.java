package com.test.designpattern.mediator;

import cn.hutool.core.lang.Console;

public class User2 extends User {

    public User2(Mediator mediator) {
        super(mediator);
    }

    @Override
    void work() {
        Console.log("用户2工作");
    }
}
