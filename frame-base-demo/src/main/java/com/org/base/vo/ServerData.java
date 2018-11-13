package com.org.base.vo;

import com.org.base.enumclass.RetCode;
import lombok.Data;

import java.util.Map;

@Data
public class ServerData<T> {

    private RetCode code;

    private RetCode msg;

    private T bo;

    private Map<String,?> other;

    public ServerData(){
        this.msg = RetCode.BUSINESS_MESSGE;
        this.code = RetCode.BUSINESS_CODE;
    }
}
