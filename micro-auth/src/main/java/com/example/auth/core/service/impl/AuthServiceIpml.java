package com.example.auth.core.service.impl;

import com.example.auth.core.entity.OAuthClientDetail;
import com.example.auth.core.entity.Role;
import com.example.auth.core.entity.User;
import com.example.auth.core.mapper.IAuthMapper;
import com.example.auth.core.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceIpml implements IAuthService {

    @Autowired
    private IAuthMapper authMapper;

    private static List<User> userList = new ArrayList<User>();

    static {
        // 模拟数据库假数据
        List<Role> roleList = new ArrayList<Role>();
        roleList.add(new Role(1, "ROLE_USER", "/test/hello")); // 角色需要添加ROLE_ 前缀，在security框架里判断hasRole，则不需要添加ROLE_前缀
        roleList.add(new Role(2, "ROLE_ADMIN", "/test/hi"));
        roleList.add(new Role(3, "ROLE_ADMIN", "/test/getPrinciple"));
        List<Role> roleList2 = new ArrayList<Role>();
        roleList2.add(new Role(1,"ROLE_USER", "/test/hello"));
        userList.add(new User(0, "刘德华", "123456", roleList));
        userList.add(new User(0, "谢霆锋", "111111", roleList));
        userList.add(new User(0, "吴彦祖", "222222", roleList2));
    }

    @Override
    public User getUserByName(String name) {
        for (User user : userList) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<OAuthClientDetail> getOAuthClientDetails(String clientId) {
        return authMapper.getOAuthClientDetails(clientId);
    }
}
