package com.alipay.sofa.isle.sample.provider2;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
