package com.org.util;

import java.util.UUID;
/**
* @Author: WangLin
* @Description: UUID
* @Date: 2018/11/28 11:13
*/
public class UUIDUtils {

    /**
     * 生成UUID
     * @return
     */
    public static String generatorUUID(){
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replaceAll("-","");
        return uuidStr;
    }
}
