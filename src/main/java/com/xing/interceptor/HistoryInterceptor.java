package com.xing.interceptor;

import com.xing.entity.History;
import com.xing.entity.User;
import com.xing.service.impl.HistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xing on 2017/9/20.
 */
@Component
public class HistoryInterceptor implements HandlerInterceptor {

    @Autowired
    private HistoryServiceImpl historyService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        History history = new History();

        String ip = request.getRemoteAddr();
        String url = request.getRequestURI();
        HttpSession session = request.getSession();


        history.setIp("0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip);
        history.setDescription(url);

        User user = (User) session.getAttribute("user");
        if (user != null) {
            System.out.println("用户为空!");
            history.setUid(user.getUid());
        }

        historyService.insertHistory(history);

        System.out.println("ip=" + ip + "&url=" + url + "&uid=");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
