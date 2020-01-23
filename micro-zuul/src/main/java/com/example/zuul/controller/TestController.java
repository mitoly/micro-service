package com.example.zuul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/login")
    public String test() {
        return "/login.html";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "/welcome.html";
    }

    @RequestMapping("/user")
    public String user() {
        return "/user.html";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "/admin.html";
    }

    @RequestMapping("/401")
    public String _401() {
        return "/401.html";
    }
}
