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
package com.alipay.sofa.isle.sample.provider.web;

import com.alipay.sofa.isle.sample.provider.api.Provider1;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author xuanbei
 * @since 2.4.5
 */
@RestController
public class Provider1Controller {

    @SofaReference
    private Provider1 provider1;

    @RequestMapping("/provider1")
    public void provider1() throws IOException {
        provider1.action();
    }
}
