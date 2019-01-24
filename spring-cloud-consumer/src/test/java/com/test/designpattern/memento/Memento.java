package com.test.designpattern.memento;

import lombok.Data;

@Data
public class Memento {
    private String value;
    public Memento(String value){
        this.value = value;
    }
}
