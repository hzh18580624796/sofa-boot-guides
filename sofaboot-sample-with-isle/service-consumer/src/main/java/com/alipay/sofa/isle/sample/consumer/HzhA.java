package com.alipay.sofa.isle.sample.consumer;

import com.alipay.sofa.isle.sample.facade.A;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
//@SofaService(uniqueId = "a")
//@SofaService
@Component
public class HzhA implements A {

    @PostConstruct
    public void init(){
        System.out.println("consumer HzhA");
    }

    public void tt() {
        System.out.println("X");
    }
}
