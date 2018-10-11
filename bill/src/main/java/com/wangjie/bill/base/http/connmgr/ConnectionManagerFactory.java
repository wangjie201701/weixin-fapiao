/**
 * 
 */
package com.wangjie.bill.base.http.connmgr;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/**
 * 连接管理器工厂
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月29日 下午3:36:16
 */
public class ConnectionManagerFactory {

    /**
     * 构建连接池管理器
     * 
     * @param checkHttps
     *            是否严禁检查https
     * @return
     */
    public static PoolingHttpClientConnectionManager buildPoolingHttpClientConnectionManager(boolean checkHttps) {
        PoolingHttpClientConnectionManager phccm = null;
        try {
            if (checkHttps) {
                phccm = new PoolingHttpClientConnectionManager();
            } else {
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new TrustManager[] { new CustomX509TrustManager() }, null);
                SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
                        NoopHostnameVerifier.INSTANCE);
                Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", sslSocketFactory).build();
                phccm = new PoolingHttpClientConnectionManager(registry);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return phccm;
    }
}
