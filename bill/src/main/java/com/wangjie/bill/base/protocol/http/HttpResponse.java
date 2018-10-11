package com.wangjie.bill.base.protocol.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wangjie.bill.base.network.LocalHost;
import com.wangjie.bill.base.protocol.MessageId;
import com.wangjie.bill.base.protocol.Response;


/**
 * 公共对象
 * @param <T>
 */
public class HttpResponse<T> implements Response {

    /**
     * 请求服务器计算机名
     */
    @JsonProperty(value = "server_machine_name")
    protected String serverMachineName = LocalHost.getMachineName();

    /**
     * 请求服务器ip
     */
    @JsonProperty(value = "server_ip")
    protected String serverIp = LocalHost.getLocalIP();

    /**
     * 请求服务器时间
     */
    @JsonProperty(value = "server_current_time")
    protected String serverCurrentTime = String.valueOf(System.currentTimeMillis());

    /**
     * 返回码, 0表示成功
     */
    @JsonProperty(value = "code")
    protected String code;

    /**
     * 信息
     */
    @JsonProperty(value = "message")
    protected String message;

    /**
     * 返回数据
     */
    @JsonProperty(value = "data")
    protected T data;

    public HttpResponse() {
    }

    public HttpResponse(T data) {
        this.code = MessageId.SUCCESS_CODE;
        this.message = MessageId.SUCCESS_MESSAGE;
        this.data = data;
    }

    public HttpResponse(MessageId id) {
        this.message = id.getMessage();
        this.code = id.getCode();
    }

    public HttpResponse(MessageId id, T data) {
        this.message = id.getMessage();
        this.code = id.getCode();
        this.data = data;
    }

    HttpResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static HttpResponse success() {
        return new HttpResponse(MessageId.SUCCESS_CODE, MessageId.SUCCESS_MESSAGE);
    }

    public static HttpResponse success(final Object data) {
        return new HttpResponse<>(data);
    }

    public static HttpResponse failure(final MessageId id) {
        return new HttpResponse<>(id);
    }

    public static HttpResponse failure(final MessageId id, final Object data) {
        return new HttpResponse<>(id, data);
    }

    public String getServerMachineName() {
        return serverMachineName;
    }

    public void setServerMachineName(String serverMachineName) {
        this.serverMachineName = serverMachineName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getServerCurrentTime() {
        return serverCurrentTime;
    }

    public void setServerCurrentTime(String serverCurrentTime) {
        this.serverCurrentTime = serverCurrentTime;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public HttpResponse setData(T data) {
        this.data = data;
        return this;
    }
}
