package com.xing.controller;


import com.xing.entity.User;
import com.xing.service.impl.MailServiceImpl;
import com.xing.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;


/**
 * Created by xing on 2017/9/3.
 */
@Controller
@RequestMapping(value = {"/user"})
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @Autowired
    private MailServiceImpl mailService;

    @Value("${spring.mail.username}")
    private String from;


    @RequestMapping("/login")
    public String login() {
        return "user/login";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signin(@RequestParam(value = "username") String username, @RequestParam("password") String password, HttpSession session, RedirectAttributes attributes) {

        String msg = "登录失败!";

        if (!StringUtils.hasLength(username)) {
            attributes.addFlashAttribute("reason", "用户名不合法!");
            return "redirect:/user/login";
        }

        User user = userService.getUserByUsername(username);

        /*
        *
        * @TODO: Shiro 验证
        * */

        if (user != null && user.getPassword().equals(password)) {
            msg = "登录成功!" + user.getEmail();
            System.out.println(msg);
            user.setGmtRegister(new Date());
            session.setAttribute("user", user);

            String originUrl = (String) session.getAttribute("originUrl");
            if (originUrl != null) {
                return "redirect:" + originUrl;
            }

            return "redirect:/manage/article";
        }

        attributes.addFlashAttribute("reason","用户名或密码错误!");
        return "redirect:/user/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/user/login";
    }

    @PostMapping("/email")
    public
    @ResponseBody
    String sendMail(@RequestParam("email") String mail) {
        String email = userService.getUserByEmail(mail);
        return email;
    }

    @RequestMapping("/forgetPassword")
    public String forgetPassword() {

        return "user/forgetPassword";
    }


    /*
    *
    * 发送验证码到邮箱
    *
    * */

    @PostMapping("/checkCode")
    public
    @ResponseBody
    String sendCheckCode(@RequestParam("email") String email, HttpSession session) {

        String checkCode = UUID.randomUUID().toString().substring(0, 6);
        session.setAttribute("checkCode", checkCode);

        System.out.println("验证码为:" + checkCode);
        String subject = "【BadBlog】 验证码";
        String content = "尊敬的BadBlog用户:<br>&nbsp;&nbsp;您的验证码为 <a>" + checkCode + "</a> ,请勿告诉他人。";

        mailService.sendCheckCode(email, subject, content);

        return "验证码";
    }


    @PostMapping("/findPassword")
    public String findPassword(@RequestParam("email") String email, @RequestParam("checkCode") String checkCode, @RequestParam("password") String password, HttpSession session,RedirectAttributes attributes) {

        String code = (String) session.getAttribute("checkCode");
        if (!checkCode.equals(code)) {
            attributes.addFlashAttribute("reason","验证码错误!");
            System.out.println("验证码校验失败!");
            return "redirect:/user/forgetPassword";
        }

        userService.updatePasswordByEmail(email, password);

        return "redirect:/user/login";
    }


    /*
    *
    *用户名是否存在
    * */
    @PostMapping("/exist")
    public
    @ResponseBody
    boolean existUsername(@RequestParam("username") String username) {

        User user = userService.getUserByUsername(username);

        return user == null;

    }

    /*
    *
    * 用户注册
    *
    * */


    @PostMapping("/addUser")
    public String signup(@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("checkCode") String code, @RequestParam("password") String password, HttpSession session,RedirectAttributes attributes) {
        String checkCode = (String) session.getAttribute("checkCode");

        if (checkCode == null || !code.equalsIgnoreCase(checkCode)) {
            attributes.addFlashAttribute("reason","验证码错误!");
            return "redirect:/user/register";
        }

        userService.saveUser(username, password, email);

        return "redirect:/user/login";
    }


    @RequestMapping("/register")
    public String register() {
        return "user/signup";
    }


}
