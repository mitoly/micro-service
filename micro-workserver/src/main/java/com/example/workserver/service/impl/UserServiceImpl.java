package com.example.workserver.service.impl;

import com.example.workserver.mapper.IUserMapper;
import com.example.workserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public Object getUserById(String id) {
        return userMapper.getUserById(id);
    }
}
