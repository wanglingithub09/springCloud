package com.test.designpattern.memento;

import lombok.Data;

@Data
public class Original {
    private String value;
    public Original(String value){
        this.value = value;
    }
    public Memento createMemento(){
        return new Memento(value);
    }
    public void restoreMemento(Memento memento){
        this.value = memento.getValue();
    }
}
