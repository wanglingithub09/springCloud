package com.test.designpattern.mediator;

import lombok.Data;

@Data
public abstract class User {
    private Mediator mediator;
    public User(Mediator mediator){
        this.mediator = mediator;
    }
    abstract void work();
}
