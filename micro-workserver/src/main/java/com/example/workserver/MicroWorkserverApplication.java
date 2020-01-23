package com.example.workserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringCloudApplication
public class MicroWorkserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroWorkserverApplication.class, args);
    }

}
