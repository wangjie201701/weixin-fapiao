package com.wangjie.bill.base.exception;

import com.wangjie.bill.base.protocol.MessageId;

public class BizException extends RuntimeException {
    private static final long serialVersionUID = 4863980120893135018L;
    
    /** 错误码 */
    private MessageId errorMsg;
        
    public BizException(MessageId errorMsg){
        super(errorMsg.getCode() + "-" + errorMsg.getMessage());
        this.errorMsg = errorMsg;
    }
    
    public static BizException build(MessageId errorMsg){
        return new BizException(errorMsg);
    }
    
    public MessageId getErrorMsg(){
        return errorMsg;
    }
    
    public String getErrorCode(){
        return errorMsg.getCode();
    }
    
    public String getErrorDesc(){
        return errorMsg.getMessage();
    }
}
