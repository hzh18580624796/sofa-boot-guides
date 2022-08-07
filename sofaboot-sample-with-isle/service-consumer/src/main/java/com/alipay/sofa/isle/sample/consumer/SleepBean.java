package com.alipay.sofa.isle.sample.consumer;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * comsumer在这里有个bean=sleepBean
 * 他的父类也有一个bean=sleepBean
 * 因为上下文隔离，各自用自己的bean
 */
@Component
public class SleepBean {

    @PostConstruct
    public void init() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
