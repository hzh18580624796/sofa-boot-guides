package com.alipay.sofa.isle.sample.provider;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Hzp {

    @PostConstruct
    public void init(){
        System.out.println("Hzp init");
    }

    public void xxx(){
        System.out.println("xxxxx  hahaha");
    }
}
