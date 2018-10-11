package com.wangjie.bill.base.protocol.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wangjie.bill.base.protocol.Request;


public class HttpRequest implements Request {

    /**
     * 请求ip
     */
    @JsonProperty(value = "request_ip")
    protected String requestIp;

    /**
     * 请求系统
     */
    @JsonProperty(value = "request_system")
    protected String requestSystem;

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getRequestSystem() {
        return requestSystem;
    }

    public void setRequestSystem(String requestSystem) {
        this.requestSystem = requestSystem;
    }
}
