package com.alipay.sofa.isle.sample.provider.impl;

import com.alipay.sofa.isle.sample.provider.api.Provider1;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import org.springframework.stereotype.Component;

@SofaService
@Component
public class Provider1Impl implements Provider1 {
    public void action() {
        System.out.println("1");
    }
}
