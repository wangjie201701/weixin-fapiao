package com.wangjie.bill.base.http;

import org.apache.http.NameValuePair;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class HttpClientPost extends AbstractHttpClient {

    protected Map<String, ContentBody> multipartParameters;

    protected HttpClientPost(final String url) {
        super(HttpMethod.POST, url);
    }

    protected HttpClientPost(final String url, final String charset) {
        super(HttpMethod.POST, url, charset);
    }

    public HttpClientPost multipart() {
        MultipartEntityBuilder multipartBuilder = MultipartEntityBuilder.create();
        if (parameters != null && !parameters.isEmpty()) {
            for (NameValuePair pair : parameters) {
                multipartBuilder.addPart(pair.getName(), new StringBody(pair.getValue(), ContentType.TEXT_PLAIN));
            }
        }
        if (multipartParameters != null && !multipartParameters.isEmpty()) {
            for (Map.Entry<String, ContentBody> entry : multipartParameters.entrySet()) {
                multipartBuilder.addPart(entry.getKey(), entry.getValue());
            }
        }
        this.requestBuilder.setEntity(multipartBuilder.build());
        return this;
    }

    public <T> HttpClientPost xml(final String body) {
        StringEntity entity = new StringEntity(body, this.requestBuilder.getCharset());
        entity.setContentType(ContentType.create(ContentType.APPLICATION_XML.getMimeType(), this.charset).toString());
        this.requestBuilder.setEntity(entity);
        return this;
    }

    public HttpClientPost addParameter(final String name, final File value) {
        if (multipartParameters == null) {
            multipartParameters = new HashMap<>();
        }
        multipartParameters.put(name, new FileBody(value));
        return this;
    }

    /**
     * 提供兼容json，和form表单的传递参数的方法，
     * 该方法是完全根据客户端请求，完全透传给服务提供者
     *
     * @param body     request中header中的body
     * @param mimeType request中header中的content-type
     * @return 返回http对象
     */
    public HttpClientPost postJsonOrForm(final String body, final String mimeType) {
        StringEntity entity = new StringEntity(body, this.requestBuilder.getCharset());
        entity.setContentType(ContentType.create(mimeType, this.charset).toString());
        this.requestBuilder.setEntity(entity);
        return this;
    }
}
