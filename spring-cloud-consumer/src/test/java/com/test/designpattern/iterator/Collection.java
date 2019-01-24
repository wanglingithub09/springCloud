package com.test.designpattern.iterator;

public interface Collection {

    Iterator iterator();
    /**新增集合元素**/
    void add(String value);
    /**取得集合元素**/
    Object get(int i);
    /**删除元素**/
    String remove(int i);
    /**取得集合大小**/
    int size();
}
