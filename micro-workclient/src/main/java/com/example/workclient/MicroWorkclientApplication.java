package com.example.workclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringCloudApplication
public class MicroWorkclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroWorkclientApplication.class, args);
    }

}
