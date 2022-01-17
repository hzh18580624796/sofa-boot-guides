package com.alipay.sofa.isle.sample.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class XXXX {

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        System.out.println("XXXX.applicationContext= " + applicationContext);
    }

}
