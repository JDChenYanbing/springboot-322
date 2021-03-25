package com.cyb.springboot.service;


import com.cyb.springboot.mapper.UserMapper;
import com.cyb.springboot.model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserBean queryUserByName(String userName) {
        return userMapper.queryUserByName(userName);
    }
}
