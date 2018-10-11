/**
 * 
 */
package com.wangjie.bill.base.http.connmgr;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 自定义校验管理器
 * @author liyinglong@hanyun.com
 * @date 2016年8月29日 下午3:59:10
 */
public class CustomX509TrustManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        // 不校验
    }

    @Override
    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        // 不校验
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

}
