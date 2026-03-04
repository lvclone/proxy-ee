package com.github.monkeywie.proxyee.intercept.common;

import com.github.monkeywie.proxyee.crt.CertUtil;
import com.github.monkeywie.proxyee.intercept.HttpProxyIntercept;
import com.github.monkeywie.proxyee.server.HttpProxyCACertFactory;
import com.github.monkeywie.proxyee.util.ByteUtil;
import com.github.monkeywie.proxyee.util.FileUtils;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.security.cert.X509Certificate;

/**
 * 处理证书下载页面 http://proxyServerIp:proxyServerPort
 */
@Slf4j
public class CertDownIntercept extends HttpProxyIntercept {

    private boolean isDirect = false;

    private X509Certificate cert = null;

    /**
     * Using proxyee's own CA certificate to construct CertDownIntercept
     */
    public CertDownIntercept() {
    }

    /**
     * Using CA public key in {@linkplain HttpProxyCACertFactory CaCertFactory} to construct CertDownIntercept
     * <p> Visitors will receive a CA certificate from CaCertFactory instead of proxyee's built-in certificate.
     *
     * @param certFactory The same factory as {@link com.github.monkeywie.proxyee.server.HttpProxyServer}
     *                    ({@link com.github.monkeywie.proxyee.server.HttpProxyServer#caCertFactory(HttpProxyCACertFactory)})
     *                    is required, otherwise HTTP processing will fail
     * @throws Exception When factory throws an exception, it will be thrown directly.
     */
    public CertDownIntercept(HttpProxyCACertFactory certFactory) throws Exception {
        this.cert = certFactory.getCACert();
    }

    /**
     * Provide certificate structure CertDownIntercept directly.
     *
     * @param caCert The public key of CA certificate consistent with
     *               {@link com.github.monkeywie.proxyee.server.HttpProxyServer}.
     */
    public CertDownIntercept(X509Certificate caCert) {
        this.cert = caCert;
    }


    public boolean beforeRequest(Channel clientChannel, HttpRequest httpRequest) throws Exception {
        if (httpRequest.uri().matches("^.*/ca.crt.*$")) {  //下载证书
            HttpResponse httpResponse = new DefaultHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK);

            byte[] bts = this.cert == null ? CertUtil
                    .loadCert(Thread.currentThread().getContextClassLoader().getResourceAsStream("ca.crt"))
                    .getEncoded() :
                    cert.getEncoded();

            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/x-x509-ca-cert");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, bts.length);
            httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
            HttpContent httpContent = new DefaultLastHttpContent();
            httpContent.content().writeBytes(bts);
            clientChannel.writeAndFlush(httpResponse);
            clientChannel.writeAndFlush(httpContent);
            clientChannel.close();
            return true;
        } else if (httpRequest.uri().matches("^.*/favicon.ico$")) {
            clientChannel.close();
            return true;
        } else if (httpRequest.uri().matches("^/$")) {  //跳转下载页面
            String html = "<html><body><div style=\"margin-top:100px;text-align:center;\"><a href=\"ca.crt\">ProxyeeRoot ca.crt</a></div></body></html>";
            html = FileUtils.readLine("./cache/pictureRecording/splidejs.com/index.html");
            ByteUtil.buildContext(clientChannel, "text/html;charset=utf-8", HttpHeaderValues.KEEP_ALIVE, html.getBytes());
            /*
                HttpResponse httpResponse = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
                httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=utf-8");
                httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, html.getBytes().length);
                httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                HttpContent httpContent = new DefaultLastHttpContent();
                httpContent.content().writeBytes(html.getBytes());
                clientChannel.writeAndFlush(httpResponse);
                clientChannel.writeAndFlush(httpContent);
            */
            return true;
        }
        return false;
    }

}
