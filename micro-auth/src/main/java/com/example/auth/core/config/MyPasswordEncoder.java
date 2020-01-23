package com.example.auth.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security再进行比对时必须要PasswordEncoder 否则报错
 * 同时也可以自定义SpringSecurity中的密码验证逻辑(目前明文对比)
 */
@Configuration
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        return encodedPassword.equals(charSequence.toString());
    }
}
