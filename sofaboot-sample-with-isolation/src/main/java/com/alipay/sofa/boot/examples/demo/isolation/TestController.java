package com.alipay.sofa.boot.examples.demo.isolation;

import com.alipay.sofa.boot.examples.demo.service.facade.SampleService;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/fire")
    public void fire(){
        ConsumerConfig<SampleService> consumerConfig = new ConsumerConfig<SampleService>()
                .setInterfaceId(SampleService.class.getName()).setProtocol("bolt")
                .setDirectUrl("127.0.0.1:9696");

        SampleService sampleService = consumerConfig.refer();

        sampleService.service();
    }
}
