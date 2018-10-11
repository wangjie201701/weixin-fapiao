package com.wangjie.bill.base.http;

import com.wangjie.bill.base.http.callback.CallbackAsBody;
import com.wangjie.bill.base.http.callback.CallbackAsResponse;
import com.wangjie.bill.base.http.callback.CallbackAsStatusCode;
import com.wangjie.bill.base.http.callback.HttpClientCallback;
import com.wangjie.bill.base.json.JsonUtil;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class HttpClientResponse {

    protected CloseableHttpResponse response;
    protected String bodyAsString;
    protected List<HttpClientCallback> callbacks;
    protected InputStream inputStream;

    protected HttpClientResponse(CloseableHttpResponse response, List<HttpClientCallback> callbacks) throws IOException {
        this.response = response;
        this.callbacks = callbacks;
        if (null != this.callbacks && !this.callbacks.isEmpty()) {
            doCallback();
        }
    }

    protected void doCallback() throws IOException {
        for (HttpClientCallback callback : this.callbacks) {
            switch (callback.getType()) {
                case RESPONSE:
                    ((CallbackAsResponse) callback).exec(this);
                    break;
                case BODY:
                    ((CallbackAsBody) callback).exec(getBody());
                    break;
                case STATUS_CODE:
                    ((CallbackAsStatusCode) callback).exec(status());
                    break;
                default:
                    throw new RuntimeException("not found callback type");
            }
        }
    }

    public int status() {
        return this.response.getStatusLine().getStatusCode();
    }

    public String getResponseHeader(final String key) {
        Header header = this.response.getLastHeader(key);
        return null != header ? header.getValue() : null;
    }

    public Header[] getResponseHeaders() {
        return this.response.getAllHeaders();
    }

    /**
     * 获取CloseableHttpResponse response的流对象
     * 使用的时候要手动关闭response对象
     *
     * @throws IOException
     */
    public InputStream getInputStream() throws IOException {
        if (null == inputStream) {
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
        }
        return inputStream;
    }

    public Boolean closeResponse() throws IOException {
        if (null != response) {
            response.close();
            return true;
        }
        return null;
    }

    //获取httpClient返回的字符串
    public String getBody() {
        if (null == bodyAsString) {
            try {
                HttpEntity entity = response.getEntity();
                try {
                    bodyAsString = EntityUtils.toString(entity, HttpClient.DEFAULT_CHARSET);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                try {
                    this.response.close();
                } catch (IOException ignored) {
                }
            }
        }
        return bodyAsString;
    }

    public String result() {
        return getBody();
    }

    public <T> T result(final Class<T> clazz) {
        if (status() < 400) {
            return JsonUtil.fromJson(result(), clazz);
        } else {
            throw new RuntimeException(this.response.getStatusLine().toString());
        }
    }
}
