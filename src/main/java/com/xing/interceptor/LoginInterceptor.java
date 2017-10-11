package com.xing.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by xing on 2017/9/4.
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        /*
        *
        * 若 url 中含 create,update,delete 等关键字 且没有登录返回登录;
        *
        * @TODO: Shiro 权限
        *
        * */

        String url = httpServletRequest.getRequestURI();

        ArrayList<String> wordList = new ArrayList<>();
        wordList.add("create");
        wordList.add("edit");
        wordList.add("publish");
        wordList.add("manage");
        wordList.add("delete");
        wordList.add("insert");
        wordList.add("exist");
        wordList.add("update");
        wordList.add("advice");

        boolean test = false;
        for (String word : wordList) {
            if (url.indexOf(word) > -1) {
                test = true;
                break;
            }
        }


        if (test) {
            HttpSession session = httpServletRequest.getSession();
            if (session.getAttribute("user") == null) {
                session.setAttribute("originUrl", url);
                httpServletResponse.sendRedirect("/user/login");
                return false;
            }
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
