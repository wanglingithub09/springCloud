package com.org.base.vo;

import com.org.base.enumclass.RetCode;
import lombok.Data;

import java.util.Map;
/**
* @Author: WangLin
* @Description: 出参实体
* @Date: 2018/11/19 10:58
*/
@Data
public class ServerData<T> {
    /**
     * 编码
     */
    private String code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 实体
     */
    private T bo;
    /**
     * map
     */
    private Map<String,?> other;

    public ServerData(){
        this.msg = RetCode.SUCCESS_CODE.getMsgId();
        this.code = RetCode.SUCCESS_CODE.getMsgCode();
    }

    public ServerData(T bo){
        this.msg = RetCode.SUCCESS_CODE.getMsgId();
        this.code = RetCode.SUCCESS_CODE.getMsgCode();
        this.bo = bo;
    }
}
