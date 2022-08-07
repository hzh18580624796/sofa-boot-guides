//package com.alipay.sofa.isle.sample.bootstrap.notspringboot;
//
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigUtils;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.util.concurrent.Executor;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class XML {
//
//    public static void main(String[] args) throws InterruptedException {
//
//        ExecutorService executor = Executors.newFixedThreadPool(3);
//
//        System.setProperty("spring.application.name", "demo");
//        System.setProperty("com.alipay.sofa.boot.active-profiles", "dev");
//
//        //DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        ApplicationContext context = new ClassPathXmlApplicationContext("CircularDependencyApplicationContext.xml");
//
//        //DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
//        //AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
//
//
//        //ApplicationContext context = new AnnotationConfigApplicationContext(GGGConfig.class);
//        //ApplicationContext context = new AnnotationConfigServletWebServerApplicationContext(GGGConfig.class);
//        //((AnnotationConfigApplicationContext) context).refresh();
//
//
//        executor.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("runing");
//            }
//        });
//
//        System.out.println(context);
//    }
//}
