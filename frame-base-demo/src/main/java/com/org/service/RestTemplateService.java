package com.org.service;

import java.util.Map;

public interface RestTemplateService {
    String post(String url, String params, Map<String,String> headerParams, Map<String,?> ...uriVariables);
    String get(String url,Map<String,?> ...uriVariables);
    void delete(String url,Map<String,?> ...uriVariables);
}
