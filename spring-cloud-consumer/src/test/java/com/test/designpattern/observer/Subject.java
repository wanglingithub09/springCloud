package com.test.designpattern.observer;

public interface Subject {
    /**新增观察者**/
    void add(Observer observer);
    /**删除观察者**/
    void delete(Observer observer);
    /**通知所有观察者**/
    void notifyObservers();
    /**自身的操作**/
    void operation();
}
