package com.newbieking.jersey_app;

import com.newbieking.Hk2AutoScanner;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Inject;

public class Application extends ResourceConfig {


    @Inject
    public Application(ServiceLocator serviceLocator) {
        packages("com.newbieking.jersey_app.resource");
        packages("com.newbieking.jersey_app.service");
        packages("com.newbieking.jersey_app.service.impl");

//        property("jersey.config.server.tracing.type", "ALL");
//        property("jersey.config.server.tracing.threshold", "VERBOSE");
//
//        register(new AbstractBinder() {
//            @Override
//            protected void configure() {
//                bind(SingletonMessageServiceImpl.class).to(MessageService.class).in(Singleton.class);
//            }
//        });

        // 自动扫描并注册服务
        Hk2AutoScanner.scanAndRegister(serviceLocator,
                "com.newbieking.jersey_app.service",  // 服务类所在包
                "com.newbieking.jersey_app.service.impl" // 其他需要扫描的包
        );
    }
}
