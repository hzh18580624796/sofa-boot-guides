package com.alipay.sofa.isle.sample.provider2.impl;

import com.alipay.sofa.isle.sample.provider2.api.Provider2;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import org.springframework.stereotype.Component;

@SofaService
@Component
public class Provider2Impl implements Provider2 {
    public void action() {
        System.out.println("2");
    }
}
