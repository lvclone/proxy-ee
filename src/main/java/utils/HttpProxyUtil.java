package utils;

import com.github.monkeywie.proxyee.config.ConfigDefault;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptInitializer;
import com.github.monkeywie.proxyee.intercept.HttpProxyInterceptPipeline;
import com.github.monkeywie.proxyee.intercept.common.CertDownIntercept;
import com.github.monkeywie.proxyee.server.HttpProxyServer;
import com.github.monkeywie.proxyee.server.HttpProxyServerConfig;
import com.github.monkeywie.proxyee.intercept.website.XHS;
import lombok.Getter;
import lombok.Setter;

public class HttpProxyUtil {

//    public HttpProxyThread httpProxyThread;
//
//    public void start() {
//        if (null != httpProxyThread) {
//            httpProxyThread = new HttpProxyThread();
//            httpProxyThread.start();
//        }
//    }
//
//    public void close() {
//        if (null != httpProxyThread) {
//            httpProxyThread.stop();
//        }
//    }
//
//    public static void main(String[] args) {
//        new HttpProxyUtil().start();
//    }
//
//    @Getter
//    @Setter
//    int port = 9999;
//
//    class HttpProxyThread extends Thread {
//        public void run() {
//            HttpProxyServerConfig config = ConfigDefault.getHttpProxyServerConfig();
//            new HttpProxyServer()
//                    .serverConfig(config)
//                    .proxyInterceptInitializer(new HttpProxyInterceptInitializer() {
//                        @Override
//                        public void init(HttpProxyInterceptPipeline pipeline) {
//                            pipeline.addLast(new CertDownIntercept());
//                            XHS.addIntercept(pipeline, null);
//                        }
//                    })
//                    .start(port);
//        }
//    }

}



