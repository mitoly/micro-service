package com.example.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableZuulProxy
@SpringCloudApplication  // 包含SpringBootApplication 等注解
public class MicroZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroZuulApplication.class, args);
    }

}
