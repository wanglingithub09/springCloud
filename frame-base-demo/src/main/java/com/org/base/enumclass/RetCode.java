package com.org.base.enumclass;

public enum RetCode {

    SUCCESS_CODE("SUCCESS_MESSAGE_CODE","0000"),
    SUCCESS_MESSAGE("SUCCESS_MESSAGE","Success"),

    BUSINESS_CODE("BUSINESS_MESSGE_CODE","0001"),
    BUSINESS_MESSGE("BUSINESS_MESSGE","Business error"),

    VALIDATIONEXCEPTION_CODE("VALIDATIONEXCEPTION_CODE","0002"),
    VALIDATIONEXCEPTION_MESSAGE("VALIDATIONEXCEPTION_MESSAGE","ValidationException Error");

    private String messageId;

    private String messageCode;

    RetCode(String messageId,String messageCode){
        this.messageId=messageId;
        this.messageCode=messageCode;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
}
