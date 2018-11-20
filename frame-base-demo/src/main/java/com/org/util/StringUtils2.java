package com.org.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
/**
* @Author: WangLin
* @Description: 字符工具类
* @Date: 2018/11/20 10:31
*/
public class StringUtils2 {

    /**
     *  Jackson library
     * @param jsonInString
     * @return
     */
    public final static boolean isJsonValid(String jsonInString) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
