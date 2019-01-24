package com.test.designpattern.iterator;

import cn.hutool.core.lang.Console;
/**
* @Author: WangLin
* @Description: 迭代子模式
 * 迭代器模式就是顺序访问聚集中的对象，一般来说，集合中非常常见，如果对集合类比较熟悉的话，
 * 理解本模式会十分轻松。这句话包含两层意思：
 * 一是需要遍历的对象，即聚集对象，
 * 二是迭代器对象，用于对聚集对象进行遍历访问
* @Date: 2019/1/18 17:13
*/
public class Test {
    public static void main(String[] args) {
        Collection collection = new MyCollection();
        collection.add("a");
        collection.add("b");
        collection.add("c");
        collection.add("d");
        collection.remove(0);
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            Console.log(iterator.next());
        }
    }
}
