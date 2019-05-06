package com.org.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
/**
* @Author: WangLin
* @Description: 服务之间通讯接口
* @Date: 2018/11/13 14:47
*/
public interface RestTemplateService {
    String post(String url, String params,Map<String, String> headerParams,Map<String,?> ...uriVariables);
    String get(String url,Map<String,?> ...uriVariables);
    String delete(String url,String params,Map<String, String> headerParams,Map<String,?> ...uriVariables);
    String put(String url,String params,Map<String, String> headerParams,Map<String,?> ...uriVariables);
}
