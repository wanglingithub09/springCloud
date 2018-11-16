package com.org.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.org.base.enumclass.RetCode;
import com.org.base.vo.ServerData;
import com.org.service.RestTemplateService;
import com.org.util.HttpConstants;
import com.org.util.StringUtils2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component("restTemplateServiceImpl")
public class RestTemplateServiceImpl implements RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceInfoImpl serviceInfoImpl;

    /**
     *
     * @param url
     * @param params 请求参数
     * @param headerParams 请求头参数
     * @param uriVariables 格式化请求url地址 例如:http://服务名/test/{name}
     * @return
     * @HystrixCommand: 用于服务断路器，如果某服务突然停掉就2s通知，解决阻塞
     */
    @HystrixCommand(fallbackMethod = "HystrixError")
    @Override
    public String post(String url,String params,Map<String,String> headerParams,Map<String,?> ...uriVariables){
        //url必须校验
        if(StringUtils.isBlank(url)){
            new Exception("url is not null");
        }
        //params必须校验
        if(StringUtils.isBlank(params)){
            params = "";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpConstants.Headers.ACCEPT,HttpConstants.Headers.APPLICATION_JSON);
        headers.add(HttpConstants.Headers.CONTENT_TYPE, HttpConstants.Headers.APPLICATION_JSON_UTF_8);
        headers.add(HttpConstants.Headers.X_TOKEN_VALUE, HttpConstants.Headers.X_TOKEN_VALUE);
        if(null != headerParams) {
            for (Map.Entry<String,String> entry: headerParams.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    headers.set(key,value);
            }
        }
        HttpEntity httpEntity = new HttpEntity(params, headers);
        ResponseEntity<String> result = null;
        try {
            Map<String,Object> map = mapArgsConvertMap(uriVariables);
            if(!map.isEmpty()){
                result = restTemplate.postForEntity(url, httpEntity, String.class, map);
            }else{
                result = restTemplate.postForEntity(url, httpEntity, String.class);
            }
            //验证响应状态
            valiResponeStatus(result);
        }catch(Exception e){
            new Exception("request falid",e);
        }
        return convertJsonVo(result.getBody(),0);
    }

    /**
     *
     * @param url
     * @param uriVariables 格式化请求url地址 例如:http://服务名/test/{name}
     * @return
     */
    @HystrixCommand(fallbackMethod = "HystrixError")
    @Override
    public String get(String url,Map<String,?> ...uriVariables){
        //url必须校验
        if(StringUtils.isBlank(url)){
            new Exception("url is not null");
        }
        ResponseEntity<String> result = null;
        try {
            Map<String,Object> map = mapArgsConvertMap(uriVariables);
            if(!map.isEmpty()){
                result = restTemplate.getForEntity(url,String.class,map);
            }else{
                result = restTemplate.getForEntity(url, String.class);
            }
            //验证响应状态
            valiResponeStatus(result);
        }catch (Exception e){
            new Exception(e);
        }
        return convertJsonVo(result.getBody(),0);
    }

    /**
     * @param url
     * @param uriVariables 格式化请求url地址 例如:http://服务名/test/{name}
     */
    @HystrixCommand(fallbackMethod = "HystrixError")
    @Override
    public String delete(String url,Map<String,?> ...uriVariables){
        //url必须校验
        if(StringUtils.isBlank(url)){
            new Exception("url is not null");
        }
        Map<String,Object> map = mapArgsConvertMap(uriVariables);
        if(!map.isEmpty()){
            restTemplate.delete(url,map);
        }else{
            restTemplate.delete(url);
        }
        return convertJsonVo("true",0);
    }

    private Map<String,Object> mapArgsConvertMap(Map<String,?> ...uriVariables){
        Map<String,Object> map = new HashMap<>();
        if(null != uriVariables){
            for(Map<String,?> uriVariable : uriVariables){
                for(Map.Entry<String,?> entry : uriVariable.entrySet()){
                    map.put(entry.getKey(),entry.getValue());
                }
            }
        }
        return map;
    }

    private void valiResponeStatus(ResponseEntity<String> result){
        if(null == result || StringUtils.isBlank(result.getBody())){
            new Exception("result VO is not null");
        }
        if (!HttpConstants.StatusCode.STATUS_CODE_200.equals(result.getStatusCode().toString())) {
            new Exception("request falid，status code:"+result.getStatusCode()+":"+result.getBody());
        }
    }

    public String HystrixError(String url,String params,Map<String,String> headerParams,Map<String,?> ...uriVariables){
        String msgVo = "request url falid: "+url+", Port:"+serviceInfoImpl.getPort()+", Service closed，Please check!";
        return convertJsonVo(msgVo,1);
    }

    private String convertJsonVo(String body,int isSuccess){
        ServerData serverData = new ServerData();
        if(StringUtils2.isJsonValid(body)){
            serverData = JSONObject.parseObject(body, ServerData.class);
        }else{
            serverData.setBo(body);
        }
        if(isSuccess == 1){
            serverData.setMsg(RetCode.BUSINESS_CODE.getMsgId());
            serverData.setCode(RetCode.BUSINESS_CODE.getMsgCode());
        }
        return JSONObject.toJSONString(serverData);
    }
}
