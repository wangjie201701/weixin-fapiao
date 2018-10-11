package com.wangjie.bill.base.http;

import com.wangjie.bill.base.http.callback.HttpClientCallback;
import com.wangjie.bill.base.http.connmgr.ConnectionManagerFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.Args;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HttpClient {

    public static final String HEADER_CONTENT_CRYPTO_TYPE = "Content-Crypto-Type";

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * 最大连接数
     */
    protected static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 1000;
    /**
     * 每个路由最大并发连接
     */
    protected static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 500;
    /**
     * 默认超时时间
     */
    protected static final int DEFAULT_TIME_OUT = 5000;

    protected static CloseableHttpClient CLIENT;

    static {
        init();
    }

    protected RequestBuilder requestBuilder;
    protected RequestConfig requestConfig;
    protected HttpClientContext context;
    protected Charset charset = DEFAULT_CHARSET;
    protected HttpMethod method;
    protected List<NameValuePair> parameters;
    protected List<HttpClientCallback> callbacks;

    protected HttpClient(final HttpMethod method, final String url) {
        this(method, url, DEFAULT_CHARSET);
    }

    protected HttpClient(final HttpMethod method, final String url, final String charset) {
        this(method, url, Charset.forName(charset));
        this.charset = Charset.forName(charset);
    }

    protected HttpClient(final HttpMethod method, final String url, final Charset charset) {
        this.charset = charset;
        this.method = method;
        this.requestBuilder = RequestBuilder.create(method.name()).setUri(url).setCharset(charset);
        this.context = HttpClientContext.create();
    }

    protected static void init() {
        PoolingHttpClientConnectionManager connectionManager =
                ConnectionManagerFactory.buildPoolingHttpClientConnectionManager(false);
        connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(connectionManager);
        // 重试次数，默认是3次，没有开启
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true));
        CLIENT = httpClientBuilder.build();
    }

    public static HttpClient exec(final HttpMethod method, final String url) {
        return new HttpClient(method, url);
    }

    public static HttpClient exec(final HttpMethod method, final String url, final String charset) {
        return new HttpClient(method, url, charset);
    }

    public static HttpClient get(final String url) {
        return new HttpClient(HttpMethod.GET, url);
    }

    public static HttpClient get(final String url, final String charset) {
        return new HttpClient(HttpMethod.GET, url, charset);
    }

    public static HttpClientPost post(final String url) {
        return new HttpClientPost(url);
    }

    public static HttpClientPost post(final String url, final String charset) {
        return new HttpClientPost(url, charset);
    }

    public static HttpClientPut put(final String url) {
        return new HttpClientPut(url);
    }

    public static HttpClientPut put(final String url, final String charset) {
        return new HttpClientPut(url, charset);
    }

    public static HttpClientDelete delete(final String url) {
        return new HttpClientDelete(url);
    }

    public static HttpClientDelete delete(final String url, final String charset) {
        return new HttpClientDelete(url, charset);
    }

    public HttpClient timeout(final int timeout) {
        return timeout(timeout, timeout);
    }

    public HttpClient timeout(final Integer connect, final Integer socket) {
        RequestConfig.Builder config = RequestConfig.custom();
        if (connect != null) {
            config.setConnectTimeout(connect);
        }
        if (socket != null) {
            config.setSocketTimeout(socket);
        }
        this.requestConfig = config.build();
        return this;
    }

    public HttpClient authentication(final String username, final String password) {
        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), new UsernamePasswordCredentials(username, password));
        this.context.setCredentialsProvider(provider);
        return this;
    }

    public HttpClient setHeader(String name, String value) {
        Args.notEmpty(name, "Header Name");
        Args.notEmpty(value, "Header Value");
        this.requestBuilder.setHeader(name, value);
        return this;
    }

    public HttpClient setUserAgent(final String userAgent) {
        return setHeader("User-Agent", userAgent);
    }

    public HttpClient addParameters(final NameValuePair... nameValuePairs) {
        for (final NameValuePair nvp : nameValuePairs) {
            addParameter(nvp);
        }
        return this;
    }

    public HttpClient addParameter(final String name, final String value) {
        return addParameter(new BasicNameValuePair(name, value));
    }

    public HttpClient addParameter(final NameValuePair nameValuePair) {
        Args.notNull(nameValuePair, "Name value pair");
        if (parameters == null) {
            parameters = new LinkedList<NameValuePair>();
        }
        parameters.add(nameValuePair);
        return this;
    }

    @SuppressWarnings("CheckStyle")
    public HttpClient callback(final HttpClientCallback... callbacks) {
        this.callbacks = Arrays.asList(callbacks);
        return this;
    }

    public HttpClientResponse action() {
        //long start = System.currentTimeMillis();
        if (null != this.parameters && !this.parameters.isEmpty()) {
            if (this.method == HttpMethod.POST || this.method == HttpMethod.PUT) {
                HttpEntity entityCopy = this.requestBuilder.getEntity();
                if (null == entityCopy) {
                    entityCopy = new UrlEncodedFormEntity(this.parameters, this.charset);
                    this.requestBuilder.setEntity(entityCopy);
                }
            } else {
                for (NameValuePair parameter : this.parameters) {
                    this.requestBuilder.addParameter(parameter);
                }
            }
        }
        HttpRequestBase request = (HttpRequestBase) this.requestBuilder.build();
        if (this.requestConfig == null) {
            timeout(DEFAULT_TIME_OUT);
        }
        request.setConfig(this.requestConfig);
        try {
            CloseableHttpResponse response = CLIENT.execute(request, this.context);
            return new HttpClientResponse(response, this.callbacks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
