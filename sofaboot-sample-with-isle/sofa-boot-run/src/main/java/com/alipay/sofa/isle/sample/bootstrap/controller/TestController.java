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
package com.alipay.sofa.isle.sample.bootstrap.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.sofa.isle.sample.facade.SampleJvmService;
import com.alipay.sofa.runtime.api.annotation.SofaReference;

/**
 * @author xuanbei
 * @since 2.4.5
 */
@RestController
public class TestController {

    //xml
    @SofaReference
    private SampleJvmService sampleJvmService;

    //@annotation
    @SofaReference(uniqueId = "annotationImplx")
    private SampleJvmService sampleJvmServiceAnnotationImpl;

    //client
    @SofaReference(uniqueId = "serviceClientImpl")
    private SampleJvmService sampleJvmServiceClientImpl;


    //# by hzh
    @SofaReference(uniqueId = "annotationImplHzh")
    private SampleJvmService annotationImplHzh;

    @SofaReference(uniqueId = "annotationImplHzp")
    private SampleJvmService annotationImplHzp;

    @RequestMapping("/serviceWithoutUniqueId")
    public String serviceWithoutUniqueId() throws IOException {
        return sampleJvmService.message();
    }

    @RequestMapping("/annotationImplService")
    public String annotationImplService() throws IOException {
        return sampleJvmServiceAnnotationImpl.message();
    }

    @RequestMapping("/serviceClientImplService")
    public String serviceClientImplService() throws IOException {
        return sampleJvmServiceClientImpl.message();
    }

    //#
    @RequestMapping("/annotationImplHzh")
    public String annotationImplHzh() throws IOException {
        return annotationImplHzh.message();
    }

    @RequestMapping("/annotationImplHzp")
    public String annotationImplHzp() throws IOException {
        return annotationImplHzp.message();
    }
}

//JVM Reference[interface com.alipay.sofa.isle.sample.facade.SampleJvmService#sampleJvmService] can not find the corresponding JVM service.
// Please check if there is a SOFA deployment publish the corresponding JVM service.
// If this exception occurred when the application starts up,
// please add Require-Module to SOFA deployment's MANIFEST.MF to indicate the startup dependency of SOFA modules.
