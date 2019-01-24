package com.test.designpattern.iterator;

public interface Iterator {
    /**前移一个元素**/
    Object previous();
    /**后移一个元素**/
    Object next();
    /**是否还有下一个元素**/
    boolean hasNext();
    /**取得第一个元素**/
    Object first();
}
