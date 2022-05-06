package com.example.workserver.controller;

import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

@RestController
@RequestMapping("/test")
public class HiController {

    Logger logger= LoggerFactory.getLogger(HiController.class);

    @Value("${server.port}")
    String port;

    //不需要任何权限，只要Header中的Token正确即可
    @RequestMapping("/hi")
    public String home() {
        return "hi :"+",i am from port:" +port;
    }

    //需要ADMIN权限，可以解析token中的Authority
    //使用@PreAuthorize注解需要在WebSecurity开启@EnableGlobalMethodSecurity(prePostEnabled = true)
//    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/hello")
    public String hello (){
        return "hello you!";
    }

    //获取当前“Token”用户信息
    @GetMapping("/getPrinciple")
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal,
                                             Authentication authentication, HttpServletRequest request){
        // 通过上下文获得用户数据
//        SecurityContext context = SecurityContextHolder.getContext();
        // 获取并解析token中的内容
        String header = request.getHeader("Authorization");
        String token = header.substring(header.indexOf("bearer") + 8);
        String tokenData = Jwts.parser().setSigningKey("key".getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody().toString();
        System.out.println(tokenData);
        logger.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        logger.info(oAuth2Authentication.toString());
        logger.info("principal.toString()"+principal.toString());
        logger.info("principal.getName()"+principal.getName());
        logger.info("authentication:"+authentication.getAuthorities().toString());

        return oAuth2Authentication;

    }

}
