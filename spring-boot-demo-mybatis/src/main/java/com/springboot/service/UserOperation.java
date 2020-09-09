package com.springboot.service;

import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;

import javax.annotation.Resource;

public class UserOperation {
    @Resource
    private UserMapper userMapper;
    public User getUserById(int id){
      return   userMapper.findOne().get(0);
    }
}
