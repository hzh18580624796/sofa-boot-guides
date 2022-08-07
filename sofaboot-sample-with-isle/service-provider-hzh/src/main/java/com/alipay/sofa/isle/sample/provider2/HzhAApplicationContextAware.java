package com.alipay.sofa.isle.sample.provider2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HzhAApplicationContextAware implements ApplicationContextAware {

    @Autowired
    private HzhA hzhA;

    @PostConstruct
    public void init(){
        hzhA.tt();
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext);
    }
}
