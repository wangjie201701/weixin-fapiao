package com.wangjie.bill.base.http;

public class HttpClientPut extends AbstractHttpClient {

    protected HttpClientPut(final String url) {
        super(HttpMethod.PUT, url);
    }

    protected HttpClientPut(final String url, final String charset) {
        super(HttpMethod.PUT, url, charset);
    }

}
