package com.example.workclient.controller;

import com.example.workclient.feign.IUserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/feign")
@RestController
public class FeignController {

    @Autowired
    IUserFeign userFeign;

    @GetMapping("/getUser/{id}")
    public Object getUserById (@PathVariable("id") String id) {
        return userFeign.getUserById(id);
    }


}
