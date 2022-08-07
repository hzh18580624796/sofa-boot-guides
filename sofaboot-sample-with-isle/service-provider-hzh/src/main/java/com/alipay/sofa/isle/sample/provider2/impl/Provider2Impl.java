package com.alipay.sofa.isle.sample.provider2.impl;

import com.alipay.sofa.isle.sample.provider2.api.Provider2;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SofaService
@Component
public class Provider2Impl implements Provider2 {

    @PostConstruct
    public void init() {
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println();
    }

    public void action() {
        System.out.println("2");
    }
}
