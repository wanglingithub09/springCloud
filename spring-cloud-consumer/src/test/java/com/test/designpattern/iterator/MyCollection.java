package com.test.designpattern.iterator;

import java.util.Vector;

public class MyCollection implements Collection{

    private Vector<String> vector = new Vector<>();

    @Override
    public Iterator iterator() {
        return new MyIterator(this);
    }

    @Override
    public void add(String value) {
        vector.add(value);
    }

    @Override
    public Object get(int i) {
        return vector.get(i);
    }

    @Override
    public String remove(int i) {
        return vector.remove(i);
    }

    @Override
    public int size() {
        return vector.size();
    }
}
