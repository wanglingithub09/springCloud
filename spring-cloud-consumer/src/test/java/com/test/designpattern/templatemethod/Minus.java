package com.test.designpattern.templatemethod;

public class Minus extends AbstractCalculate {
    @Override
    public int calculate(int a, int b) {
        return a-b;
    }
}
