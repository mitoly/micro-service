package com.example.auth.core.custom;

import com.example.auth.core.entity.User;
import com.example.auth.core.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户信息，实现SpringSecurity用户类的规范
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private IAuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authService.getUserByName(username); // 目前伪代码
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        return user;
    }
}
