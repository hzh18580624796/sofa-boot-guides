package com.alipay.sofa.isle.sample.provider.impl;

import com.alipay.sofa.isle.sample.provider.Hzp;
import com.alipay.sofa.isle.sample.provider.api.ProviderXXXXX;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SofaService
@Component
public class ProviderXXXXXImpl implements ProviderXXXXX {

    @Autowired
    private Hzp hzp;

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
        hzp.xxx();
        System.out.println("xxxxx1111");
        System.out.println("xxxxxhzhzh");
        System.out.println("内存地址：" + this);
    }
}
