package com.newbieking;


import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.jvnet.hk2.annotations.Contract;
import org.jvnet.hk2.annotations.Service;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

public class Hk2AutoScanner {
    /**
     * 自动扫描并注册指定包下的所有服务
     */
    public static void scanAndRegister(ServiceLocator locator, String... packageNames) {
        // 配置 Reflections 扫描器
        ConfigurationBuilder config = new ConfigurationBuilder()
                .forPackages(packageNames)
                .setScanners(Scanners.SubTypes, Scanners.TypesAnnotated);

        Reflections reflections = new Reflections(config);

        // 1. 扫描所有带有 @Service 注解的类
        Set<Class<?>> serviceClasses = reflections.getTypesAnnotatedWith(
                Service.class
        );

        // 2. 扫描所有实现了 @Contract 接口的类
        Set<Class<?>> contractClasses = reflections.getTypesAnnotatedWith(
                Contract.class
        );

        // 3. 注册服务到 HK2
        ServiceLocatorUtilities.bind(locator, new AbstractBinder() {
            @Override
            protected void configure() {
                // 注册 @Service 类
                for (Class<?> serviceClass : serviceClasses) {
                    // 查找该服务实现的所有 @Contract 接口
                    for (Class<?> contract : findContracts(serviceClass, contractClasses)) {
                        bind(serviceClass).to(contract);
                    }
                }
            }
        });
    }

    /**
     * 查找类实现的所有 @Contract 接口
     */
    private static Set<Class<?>> findContracts(Class<?> clazz, Set<Class<?>> contractClasses) {
        return contractClasses.stream()
                .filter(contract -> contract.isAssignableFrom(clazz))
                .collect(java.util.stream.Collectors.toSet());
    }
}
