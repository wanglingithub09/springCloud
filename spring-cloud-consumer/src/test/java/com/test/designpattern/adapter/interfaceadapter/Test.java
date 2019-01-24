package com.test.designpattern.adapter.interfaceadapter;

public class Test {
    public static void main(String[] args) {
        Sourceable sourceable = new SourceSub1();
        Sourceable sourceable1 = new SourceSub2();
        sourceable.method1();
        sourceable.method2();
        sourceable1.method1();
        sourceable1.method2();
    }
}
