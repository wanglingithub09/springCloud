package com.test.designpattern.templatemethod;

import cn.hutool.core.lang.Console;

public class Test {
    public static void main(String[] args) {
        AbstractCalculate ac = new Plus();
        int value = ac.calculate("8+8","\\+");
        Console.log(value);
        ac = new Minus();
        value = ac.calculate("8-8","-");
        Console.log(value);
    }
}
