package com.example.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

//@EnableResourceServer
@SpringCloudApplication
public class MicroAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroAuthApplication.class, args);
    }

}
