package com.alipay.sofa.isle.sample.provider2;

import com.alipay.sofa.isle.sample.facade.A;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
//@SofaService(uniqueId = "a")
//@SofaService
@Component
public class HzhA implements A {

    @PostConstruct
    public void init() {
        System.out.println("provider2 HzhA");
    }

    public void tt() {
        System.out.println("B");
    }

}
