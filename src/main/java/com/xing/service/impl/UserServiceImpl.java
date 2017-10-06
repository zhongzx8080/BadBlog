package com.xing.service.impl;

import com.xing.entity.User;
import com.xing.mapper.UserMapper;
import com.xing.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xing on 2017/9/5.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer uid) {
        return userMapper.getUserById(uid);
    }

    @Override
    public String getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public void updatePasswordByEmail(String email, String password) {
        userMapper.updatePasswordByEmail(email, password);
    }

    @Override
    public int saveUser(String username, String password, String email) {
        return userMapper.saveUser(username,password,email);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
