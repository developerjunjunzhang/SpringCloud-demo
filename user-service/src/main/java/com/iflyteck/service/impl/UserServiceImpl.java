package com.iflyteck.service.impl;

import com.iflyteck.mapper.UserMapper;
import com.iflyteck.pojo.User;
import com.iflyteck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
