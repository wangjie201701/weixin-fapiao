package com.wangjie.bill.base.http;


public class HttpClientDelete extends AbstractHttpClient {

    protected HttpClientDelete(final String url) {
        super(HttpMethod.DELETE, url);
    }

    protected HttpClientDelete(final String url, final String charset) {
        super(HttpMethod.DELETE, url, charset);
    }
}
