package com.xing.service;

import org.springframework.stereotype.Service;

/**
 * Created by xing on 2017/9/19.
 */
@Service
public interface IMailService {

    void sendCheckCode(String to, String subject, String content);

}
