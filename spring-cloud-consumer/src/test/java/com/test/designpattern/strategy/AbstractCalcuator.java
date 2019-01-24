package com.test.designpattern.strategy;

public abstract class AbstractCalcuator implements ICalculator{

    public int[] split(String exp,String opt){
        String[] arry = exp.split(opt);
        int[] arrayInt = new int[2];
        arrayInt[0] = Integer.valueOf(arry[0]);
        arrayInt[1] = Integer.valueOf(arry[1]);
        return arrayInt;
    }
}
