package com.alipay.sofa.isle.sample.consumer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@ComponentScan("com.alipay.sofa.isle.sample.consumer")
@Configuration
public class ComponentScanConfig {

    @PostConstruct
    public void tt() {
        System.out.println("...");
    }

}
