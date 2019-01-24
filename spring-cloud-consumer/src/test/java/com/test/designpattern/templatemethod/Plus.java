package com.test.designpattern.templatemethod;

public class Plus extends AbstractCalculate {
    @Override
    public int calculate(int a, int b) {
        return a+b;
    }
}
