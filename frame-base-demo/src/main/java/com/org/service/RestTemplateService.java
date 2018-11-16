package com.org.service;

import java.util.Map;
/**
* @Author: WangLin
* @Description: 
* @Date: 2018/11/13 14:47
*/
public interface RestTemplateService {
    String post(String url, String params, Map<String,String> headerParams, Map<String,?> ...uriVariables);
    String get(String url,Map<String,?> ...uriVariables);
    String delete(String url,Map<String,?> ...uriVariables);
}
