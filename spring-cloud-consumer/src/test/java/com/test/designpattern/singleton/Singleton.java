package com.test.designpattern.singleton;

/**
* @Author: WangLin
* @Description: 单利模式
* @Date: 2019/1/17 14:57
*/
public class Singleton {

    private static Singleton instance = null;

    private Singleton(){

    }

    private static synchronized void syncInit() {
        if (instance == null) {
            instance = new Singleton();
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            syncInit();
        }
        return instance;
    }

    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return getInstance();
    }

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance()==Singleton.getInstance()&&Singleton.getInstance()==Singleton.getInstance());
    }
}
