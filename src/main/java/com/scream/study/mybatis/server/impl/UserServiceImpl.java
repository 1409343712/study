package com.scream.study.mybatis.server.impl;

import com.scream.study.mybatis.bean.User;
import com.scream.study.mybatis.mapper.UserMapper;
import com.scream.study.mybatis.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
}
