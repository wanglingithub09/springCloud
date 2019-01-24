package com.test.designpattern.flyweight;

import cn.hutool.core.lang.Console;

public class Test {
    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool();
        pool.getConnection();
        Console.log(pool.pool.size());
        pool.release();
        Console.log(pool.pool.size());
    }
}
