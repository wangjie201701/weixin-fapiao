package com.wangjie.bill.base.protocol;

public class MessageId {

    public static final String SUCCESS_CODE = "0";
    public static final String SUCCESS_MESSAGE = "SUCCESS";

    private String code;
    private String message;

    protected MessageId(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public static MessageId create(final Project project, final int code, final String message) {
        return new MessageId(String.format("%s%04d", project.getNo(), code), message);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
