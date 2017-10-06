package com.xing.service;

import com.xing.entity.User;
import org.springframework.stereotype.Service;

/**
 * Created by xing on 2017/9/3.
 */
@Service
public interface IUserService {

    User getUserById(Integer uid);

    User getUserByUsername(String username);

    String getUserByEmail(String email);

    void updatePasswordByEmail(String email,  String password);

    int saveUser(String username, String password, String email);
}
