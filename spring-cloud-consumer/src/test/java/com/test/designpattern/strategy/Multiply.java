package com.test.designpattern.strategy;

public class Multiply extends AbstractCalcuator implements ICalculator {
    @Override
    public int calculate(String exp) {
        int[] arrayInt = split(exp,"\\*");
        return arrayInt[0]*arrayInt[1];
    }
}
