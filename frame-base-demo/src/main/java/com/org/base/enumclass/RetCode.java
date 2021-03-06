package com.org.base.enumclass;
/**
* @Author: WangLin
* @Description: 返回 消息/码
* @Date: 2018/11/20 10:22
*/
public enum  RetCode {

    SUCCESS_CODE("Success","0000"),
    BUSINESS_CODE("Business error","0001"),
    VALIDATIONEXCEPTION_CODE("ValidationException Error","0002");

    private String msgId;

    private String msgCode;

    RetCode(String msgId,String msgCode){
        this.msgId=msgId;
        this.msgCode=msgCode;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }
}
