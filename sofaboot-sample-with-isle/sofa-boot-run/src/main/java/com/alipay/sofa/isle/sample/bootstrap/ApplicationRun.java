/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.isle.sample.bootstrap;

import com.alipay.sofa.isle.sample.bootstrap.controller.TestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xuanbei 18/5/5
 */
@SpringBootApplication
//@ComponentScan("com.alipay.sofa.isle.sample")
@ComponentScan({"com.alipay.sofa.isle.sample.provider.web","com.alipay.sofa.isle.sample.consumer.web",
        "com.alipay.sofa.isle.sample.bootstrap"})
public class ApplicationRun {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        TestController testController = new TestController();

        System.out.println(SpringApplication.class.getClass().getPackage());

        SpringApplication springApplication = new SpringApplication(ApplicationRun.class);
        springApplication.run(args);
        long end = System.currentTimeMillis();

        System.out.println("costTime=" + (end - start));

    }
}
//20731
//16620

//7604


//19858
