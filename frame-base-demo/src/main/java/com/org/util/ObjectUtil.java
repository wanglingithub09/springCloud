package com.org.util;

import java.io.*;
/**
* @Author: WangLin
* @Description: 对象互转byte[]
* @Date: 2018/12/5 11:42
*/
public class ObjectUtil {
    /**
     * 对象转byte[]
     * @param obj
     * @return
     * @throws IOException
     **/
    public static byte[] object2Bytes(Object obj) throws IOException {
        ByteArrayOutputStream bo=new ByteArrayOutputStream();
        ObjectOutputStream oo=new ObjectOutputStream(bo);
        oo.writeObject(obj);
        byte[] bytes=bo.toByteArray();
        bo.close();
        oo.close();
        return bytes;
    }
    /**
     * byte[]转对象
     * @param bytes
     * @return
     * @throws Exception
     */
    public static Object bytes2Object(byte[] bytes) throws Exception{
        ByteArrayInputStream in=new ByteArrayInputStream(bytes);
        ObjectInputStream sIn=new ObjectInputStream(in);
        return sIn.readObject();
    }
}
