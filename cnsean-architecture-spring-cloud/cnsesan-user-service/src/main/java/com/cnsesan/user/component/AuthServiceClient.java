package com.cnsesan.user.component;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

//AuthServiceClient类，他是个接口，使用Feign向 uaa 去请求，同时加以熔断机制进行处理
@FeignClient(value="uaa-service", fallback =AuthServiceHystrix.class )
public interface AuthServiceClient {

    @PostMapping(value ="/oauth/token")
    JWT getToken(@RequestHeader(value="Authorization")String authorization,
            @RequestParam("grant_type")String type,
            @RequestParam("username")String username,
            @RequestParam("password")String password);
}	