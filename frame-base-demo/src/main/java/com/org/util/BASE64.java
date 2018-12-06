package com.org.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
* @Author: WangLin
* @Description: java8 Base64加解密
* @Date: 2018/11/27 10:13
*/
public final class BASE64 {

    /**
     * base64加密
     * @param key
     * @return
     */
    public static String encryptBASE64(byte[] key){
        if(null != key && key.length > 0){
            return Base64.getEncoder().encodeToString(key);
        }
        return null;
    }

    /**
     * base64解密
     * @param key
     * @return
     */
    public static byte[] decryptBASE64(String key){
        if(null != key && !key.isEmpty()){
            return Base64.getDecoder().decode(key);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

        String key = encryptBASE64("jdbc:mysql://10.0.55.159:3306/mango_dev?useUnicode=true&useSSL=false&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true".getBytes(StandardCharsets.UTF_8));
        System.out.println(key);
        System.out.println(new String(decryptBASE64(key),StandardCharsets.UTF_8));

    }

}
