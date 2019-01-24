package com.test.designpattern.templatemethod;

public abstract class AbstractCalculate {

    public abstract int calculate(int a,int b);

    private int[] split(String exp,String opt){
        String[] array = exp.split(opt);
        int arrayInt[] = new int[2];
        arrayInt[0] = Integer.valueOf(array[0]);
        arrayInt[1] = Integer.valueOf(array[1]);
        return arrayInt;
    }

    public final int calculate(String exp,String opt){
        int[] arrayInt = split(exp,opt);
        return calculate(arrayInt[0],arrayInt[1]);
    }
}
