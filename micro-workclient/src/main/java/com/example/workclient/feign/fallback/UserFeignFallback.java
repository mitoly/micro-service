package com.example.workclient.feign.fallback;

import com.example.workclient.feign.IUserFeign;
import org.springframework.stereotype.Component;

@Component
public class UserFeignFallback implements IUserFeign {


    @Override
    public String getUserById(String id) {
        return "熔断报错：id = " + id;
    }
}
