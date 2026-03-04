package com.github.monkeywie.proxyee;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.github.monkeywie.proxyee.mongodb.service.HttpCacheService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.annotation.Resource;

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
//            HttpProxyServerConfig config = ConfigDefault.getHttpProxyServerConfig();
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
//
//
//        };
//    }


}

