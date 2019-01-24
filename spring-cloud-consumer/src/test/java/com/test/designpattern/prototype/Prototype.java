package com.test.designpattern.prototype;

import java.io.*;

public class Prototype implements Cloneable,Serializable {

    private static final long serialVersionUID = 2145466628778936083L;
    private String field="11";

    public Object clone() throws CloneNotSupportedException {
        Prototype prototype = (Prototype) super.clone();
        return  prototype;
    }

    public Object deepClone() throws IOException, ClassNotFoundException {
        //写入当前对象的二进制流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

    public static void main(String[] args) throws Exception {
        Prototype prototype = new Prototype();
        Prototype prototype1 = (Prototype) prototype.clone();
        System.out.println(prototype.field.equals(prototype1.field));

        //-----deepClone-----
        Prototype prototype2 = new Prototype();
        Prototype prototype3 = (Prototype) prototype2.deepClone();
        System.out.println(prototype2.field.equals(prototype3.field));
    }
}
