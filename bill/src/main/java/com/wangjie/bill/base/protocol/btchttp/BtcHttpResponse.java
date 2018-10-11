package com.wangjie.bill.base.protocol.btchttp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wangjie.bill.base.network.LocalHost;
import com.wangjie.bill.base.protocol.MessageId;
import com.wangjie.bill.base.protocol.Response;

/**
 * @description: 接收btc矿池数据
 * @author: wangjie@jieshifinance.com
 * @create: 2018-07-12 13:35
 **/
public class BtcHttpResponse<T> implements Response {

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
    @JsonProperty(value = "err_no")
    protected String errNo;

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

    public BtcHttpResponse() {
    }

    public BtcHttpResponse(T data) {
        this.errNo = MessageId.SUCCESS_CODE;
        this.message = MessageId.SUCCESS_MESSAGE;
        this.data = data;
    }

    public BtcHttpResponse(MessageId id) {
        this.message = id.getMessage();
        this.errNo = id.getCode();
    }

    public BtcHttpResponse(MessageId id, T data) {
        this.message = id.getMessage();
        this.errNo = id.getCode();
        this.data = data;
    }

    BtcHttpResponse(String code, String message) {
        this.errNo = code;
        this.message = message;
    }

    public static BtcHttpResponse success() {
        return new BtcHttpResponse(MessageId.SUCCESS_CODE, MessageId.SUCCESS_MESSAGE);
    }

    public static BtcHttpResponse success(final Object data) {
        return new BtcHttpResponse<>(data);
    }

    public static BtcHttpResponse failure(final MessageId id) {
        return new BtcHttpResponse<>(id);
    }

    public static BtcHttpResponse failure(final MessageId id, final Object data) {
        return new BtcHttpResponse<>(id, data);
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

    public String getErrNo() {
        return errNo;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public BtcHttpResponse setData(T data) {
        this.data = data;
        return this;
    }


}
