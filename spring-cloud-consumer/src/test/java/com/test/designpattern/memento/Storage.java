package com.test.designpattern.memento;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Storage {
    private List<Memento> mementoList = new ArrayList<>();
    public Storage(Memento memento){
        this.mementoList.add(memento);
    }
    public void addMemento(Memento memento){
        mementoList.add(memento);
    }
}
