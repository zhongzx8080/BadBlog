package com.xing.service.impl;

import com.xing.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by xing on 2017/9/19.
 */

@Service
public class MailServiceImpl implements IMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendCheckCode(String to, String subject, String content) {

        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content,true);
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}
