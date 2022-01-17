package com.alipay.sofa.isle.sample.consumer;

import com.alipay.sofa.isle.sample.facade.SampleJvmService;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HzhController implements ApplicationContextAware {

    @SofaReference(uniqueId = "annotationImplx")
    private SampleJvmService sampleJvmServiceByFieldAnnotation;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TTT ttt;

    @GetMapping("/hzh")
    public String hzh(){
        return "hzh";
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        System.out.println(applicationContext);
    }
}
