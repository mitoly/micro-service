package com.example.workclient.feign;

import com.example.workclient.feign.fallback.UserFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "micro-workserver", fallback = UserFeignFallback.class)
public interface IUserFeign {

    @GetMapping("/user/getUser/{id}")
    public Object getUserById(@PathVariable("id") String id);


}
