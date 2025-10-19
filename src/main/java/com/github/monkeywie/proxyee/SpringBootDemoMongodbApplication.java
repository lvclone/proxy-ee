package com.github.monkeywie.proxyee;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.github.monkeywie.proxyee.mongodb.service.HttpCacheService;
import com.github.monkeywie.proxyee.server.HttpProxyServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <p>
 * 启动器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-28 16:14
 */
@SpringBootApplication
public class SpringBootDemoMongodbApplication {

    @Resource
    HttpCacheService httpCacheService;


    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoMongodbApplication.class, args);
    }

    @Bean
    public Snowflake snowflake() {
        return IdUtil.createSnowflake(1, 1);
    }


//    @Bean
//    public CommandLineRunner run() {
//        return args -> {
//            // 在这里编写你启动后想要执行的代码
//            System.out.println("Application started with arguments: " + args);
//
//            HttpProxyServerConfig config = getHttpProxyServerConfig();
//            new HttpProxyServer()
//                    .serverConfig(config)
//                    .proxyInterceptInitializer(new HttpProxyInterceptInitializer() {
//                        @Override
//                        public void init(HttpProxyInterceptPipeline pipeline) {
//                            pipeline.addLast(new CertDownIntercept());
//                            XHS.addIntercept(pipeline, httpCacheService);
//                        }
//                    })
//                    .start(9999);
//        };
//    }

    private static HttpProxyServerConfig getHttpProxyServerConfig() {
        HttpProxyServerConfig config = new HttpProxyServerConfig();
        config.setHandleSsl(true);
        // 设置Ciphers 用于改变 Client Hello 握手协议指纹
        Set<String> defaultCiphers = new LinkedHashSet<String>();
        defaultCiphers.add("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256");
        defaultCiphers.add("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
        defaultCiphers.add("TLS_RSA_WITH_AES_128_CBC_SHA");
        defaultCiphers.add("TLS_RSA_WITH_AES_128_GCM_SHA256");
        defaultCiphers.add("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
        config.setCiphers(defaultCiphers);
        return config;
    }

}

