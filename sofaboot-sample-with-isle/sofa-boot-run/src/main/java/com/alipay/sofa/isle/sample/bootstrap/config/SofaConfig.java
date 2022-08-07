//package com.alipay.sofa.isle.sample.bootstrap.config;
//
//import com.alipay.sofa.isle.sample.bootstrap.SofaURLClassLoader;
//import com.alipay.sofa.runtime.SofaFramework;
//import com.alipay.sofa.runtime.api.client.ReferenceClient;
//import com.alipay.sofa.runtime.api.client.ServiceClient;
//import com.alipay.sofa.runtime.client.impl.ClientFactoryImpl;
//import com.alipay.sofa.runtime.component.impl.StandardSofaRuntimeManager;
//import com.alipay.sofa.runtime.service.client.ReferenceClientImpl;
//import com.alipay.sofa.runtime.service.client.ServiceClientImpl;
//import com.alipay.sofa.runtime.spi.binding.BindingAdapterFactory;
//import com.alipay.sofa.runtime.spi.client.ClientFactoryInternal;
//import com.alipay.sofa.runtime.spi.component.SofaRuntimeContext;
//import com.alipay.sofa.runtime.spi.component.SofaRuntimeManager;
//import com.alipay.sofa.runtime.spi.service.BindingConverterFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import sun.misc.Launcher;
//
//@Configuration
//public class SofaConfig {
//
//    @Bean
//    public static SofaRuntimeContext sofaRuntimeContext(@Value("${spring.application.name}") String appName,
//                                                        BindingConverterFactory bindingConverterFactory,
//                                                        BindingAdapterFactory bindingAdapterFactory) {
//        ClientFactoryInternal clientFactoryInternal = new ClientFactoryImpl();
//
////        SofaRuntimeManager sofaRuntimeManager = new StandardSofaRuntimeManager(appName, new Launcher().getClassLoader(), clientFactoryInternal);
//        SofaRuntimeManager sofaRuntimeManager = new StandardSofaRuntimeManager(appName, Thread.currentThread().getContextClassLoader(), clientFactoryInternal);
//        //SofaRuntimeManager sofaRuntimeManager = new StandardSofaRuntimeManager(appName, new SofaURLClassLoader(), clientFactoryInternal);
//
//        sofaRuntimeManager
//                .getComponentManager()
//                .registerComponentClient
//                (ReferenceClient.class, new ReferenceClientImpl(sofaRuntimeManager.getSofaRuntimeContext(), bindingConverterFactory, bindingAdapterFactory));
//
//        sofaRuntimeManager
//                .getComponentManager()
//                .registerComponentClient
//                (ServiceClient.class, new ServiceClientImpl(sofaRuntimeManager.getSofaRuntimeContext(), bindingConverterFactory, bindingAdapterFactory));
//
//        SofaFramework.registerSofaRuntimeManager(sofaRuntimeManager);
//        return sofaRuntimeManager.getSofaRuntimeContext();
//    }
//}
