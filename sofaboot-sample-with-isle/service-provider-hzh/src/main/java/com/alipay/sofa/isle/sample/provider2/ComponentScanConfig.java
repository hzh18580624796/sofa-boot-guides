package com.alipay.sofa.isle.sample.provider2;

import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@ComponentScan(value = "com.alipay.sofa.isle.sample.provider2", nameGenerator = DefaultBeanNameGenerator.class)
@ComponentScan(value = "com.alipay.sofa.isle.sample.provider2")
@Configuration
public class ComponentScanConfig {
}
