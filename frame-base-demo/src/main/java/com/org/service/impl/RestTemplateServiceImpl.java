package com.org.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.org.base.enumclass.RetCode;
import com.org.base.vo.ServerData;
import com.org.constants.HttpConstants;
import com.org.service.RestTemplateService;
import com.org.service.ServicePort;
import com.org.util.StringHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
/**
* @Author: WangLin
* @Description: 使用RestTemplate来各服务之间调用，post，get，delete，put
* @Date: 2018/11/20 10:24
*/
@Component("restTemplateServiceImpl")
public class RestTemplateServiceImpl implements RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServicePort servicePortImpl;

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
    public String post(String url, String params, Map<String, String> headerParams,Map<String,?> ...uriVariables){
        //url必须校验
        if(StringUtils.isBlank(url)){
            new Exception("url is not null");
        }
        //params必须校验
        if(StringUtils.isBlank(params)){
            params = "";
        }
        HttpEntity httpEntity = getHttpEntity(params, headerParams);
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
     * @HystrixCommand: 用于服务断路器，如果某服务突然停掉就2s通知，解决阻塞
     */
    @HystrixCommand(fallbackMethod = "getHystrixError")
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
    public String delete(String url,String params, Map<String, String> headerParams,Map<String,?> ...uriVariables){
        //url必须校验
        if(StringUtils.isBlank(url)){
            new Exception("url is not null");
        }
        Map<String,Object> map = mapArgsConvertMap(uriVariables);
        HttpEntity httpEntity = getHttpEntity(params, headerParams);
        ResponseEntity<String> result = null;
        if(!map.isEmpty()){
            result = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, String.class,map);
        }else{
            result = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, String.class);
        }
        return convertJsonVo(result.getBody(),0);
    }

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
    public String put(String url,String params,Map<String, String> headerParams,Map<String,?> ...uriVariables){
        //url必须校验
        if(StringUtils.isBlank(url)){
            new Exception("url is not null");
        }
        Map<String,Object> map = mapArgsConvertMap(uriVariables);
        HttpEntity httpEntity = getHttpEntity(params, headerParams);
        ResponseEntity<String> result = null;
        if(!map.isEmpty()){
            result = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class,map);
        }else{
            result = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
        }
        return convertJsonVo(result.getBody(),0);
    }

    /**
     * 服务断路器，参数要与服务接口一致
     * @param url
     * @param params
     * @param headerParams
     * @param uriVariables
     * @return
     */
    public String HystrixError(String url, String params,Map<String, String> headerParams,Map<String,?> ...uriVariables){
        String msgVo = "request url falid: "+url+", Port:"+servicePortImpl.getPort()+", Service closed，Please check!";
        return convertJsonVo(msgVo,1);
    }

    /**
     * 服务断路器，参数要与服务接口一致
     * @param url
     * @param uriVariables
     * @return
     */
    public String getHystrixError(String url, Map<String,?> ...uriVariables){
        String msgVo = "request url falid: "+url+", Port:"+servicePortImpl.getPort()+", Service closed，Please check!";
        return convertJsonVo(msgVo,1);
    }

    /**
     * 请求参数
     * @param params 参数
     * @param headerParams 请求头参数
     * @return
     */
    private HttpEntity getHttpEntity(String params, Map<String, String> headerParams) {
        HttpHeaders headers = new HttpHeaders();
        //headers.add(HttpConstants.Headers.X_USER_ID,request.getHeader(HttpConstants.Headers.X_USER_ID));
        headers.add(HttpConstants.Headers.SKIP_VALIDATION,HttpConstants.Headers.SKIP_VALIDATION);
        headers.add(HttpConstants.Headers.ACCEPT,HttpConstants.Headers.APPLICATION_JSON);
        headers.add(HttpConstants.Headers.CONTENT_TYPE, HttpConstants.Headers.APPLICATION_JSON_UTF_8);
        headers.add(HttpConstants.Headers.X_TOKEN_VALUE, HttpConstants.Headers.X_TOKEN_VALUE);
        if(null != headerParams) {
            for (Map.Entry<String,String> entry: headerParams.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                headers.add(key,value);
            }
        }
        return new HttpEntity(params, headers);
    }

    /**
     * 转换实体字符串
     * @param body
     * @param isSuccess
     * @return
     */
    private String convertJsonVo(String body,int isSuccess){
        ServerData serverData = new ServerData();
        if(StringHelper.isJsonValid(body)){
            serverData = JSONObject.parseObject(body, ServerData.class);
        }else{
            serverData.setBo(body);
        }
        if(isSuccess == 1){
            serverData.setMsg(RetCode.BUSINESS_CODE.getMsgId());
            serverData.setCode(RetCode.BUSINESS_CODE.getMsgCode());
        }
        String resultJsonStr = JSONObject.toJSONString(serverData);
        return resultJsonStr;
    }

    /**
     * map数组转map对象
     * @param uriVariables
     * @return
     */
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

    /**
     * 验证响应状态码，200 OK
     * @param result
     */
    private void valiResponeStatus(ResponseEntity<String> result){
        if(null == result || StringUtils.isBlank(result.getBody())){
            new Exception("result VO is not null");
        }
        if (!HttpConstants.StatusCode.STATUS_CODE_200.equals(result.getStatusCode().toString())) {
            new Exception("request falid，status code:"+result.getStatusCode()+":"+result.getBody());
        }
    }
}
