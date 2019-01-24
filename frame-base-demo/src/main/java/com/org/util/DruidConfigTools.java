package com.org.util;

import com.alibaba.druid.filter.config.ConfigTools;

/**
* @Author: WangLin
* @Description: 数据库加解密
* @Date: 2018/11/27 10:49
*/
public final class DruidConfigTools{

    private static String privateKey;
    private static String publicKey;

    /**
     * 生成privateKey、publicKey
     * @return
     * @throws Exception
     */
    private static String[] genKeyPair() throws Exception {
        String[] keyPair =ConfigTools.genKeyPair(512);
        privateKey = keyPair[0];
        publicKey = keyPair[1];
        return keyPair;
    }

    /**
     * 加密
     * @param text
     * @return
     */
    public static String encryptCode(String text){
        try {
            return ConfigTools.encrypt(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param key
     * @return
     */
    public static String decryptCode(String key){
        try {
            return ConfigTools.decrypt(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(decryptCode(encryptCode("123456")));
    }

}
