package com.org.base.vo;

import com.org.base.enumclass.RetCode;
import lombok.Data;

import java.util.Map;

@Data
public class ServerData<T> {

    private String code;

    private String msg;

    private T bo;

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
