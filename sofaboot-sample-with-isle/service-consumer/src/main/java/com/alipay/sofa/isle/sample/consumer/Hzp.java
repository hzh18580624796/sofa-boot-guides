package com.alipay.sofa.isle.sample.consumer;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Hzp {


    @PostConstruct
    public void init(){
        System.out.println("consumer hzp");
    }
}
