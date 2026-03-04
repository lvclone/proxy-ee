package com.github.monkeywie.proxyee.util.github.http2;

import okhttp3.OkHttpClient;

import javax.net.ssl.*;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public class SSL {

    public static void setSSLSocketFactory(OkHttpClient.Builder clientBuilder) {
        //支持HTTPS请求，跳过证书验证 【getX509TrustManager、TLSv1.2改为SSL就是解决jdk9问题】
        clientBuilder.sslSocketFactory(createSSLSocketFactory(), getX509TrustManager());
    }

    /**
     * 生成安全套接字工厂，用于https请求的证书跳过
     */
    public static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            // SSLContext sc = SSLContext.getInstance("TLSv1.2");
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
//            logger.error("", e);
        }
        return ssfFactory;
    }

    public static X509TrustManager getX509TrustManager() {
        X509TrustManager trustManager = null;
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
            trustManager = (X509TrustManager) trustManagers[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        return trustManager;
    }

}

/**
 * 用于信任所有证书
 */
class TrustAllCerts implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
        //
    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
        //
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[]{};
    }
}

