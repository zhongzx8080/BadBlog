package com.xing.interceptor;

import com.xing.entity.History;
import com.xing.entity.User;
import com.xing.service.impl.HistoryServiceImpl;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by xing on 2017/9/20.
 */
@Component
public class HistoryInterceptor implements HandlerInterceptor {

    @Autowired
    private HistoryServiceImpl historyService;


    private final String LOCATIONAPI = "http://freeapi.ipip.net/";
    private HashMap<String, String> locationMap = null;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        History history = new History();

        String host = request.getRemoteHost();
        String ip = request.getRemoteAddr();
        String url = request.getRequestURI();
        HttpSession session = request.getSession();


        ip = "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
        history.setIp(ip);
        history.setDescription(url);


        //获取 ip 归属地
        if (locationMap == null) {
            locationMap = historyService.getAllHistory();
        }

        // 先在 locationMap 中查找，没找到在 jsoup 获取
        String location = locationMap.get(ip);
        if (location == null) {
            location = Jsoup.connect(LOCATIONAPI + ip)
                    .header("Accept", "text/javascript")
                    .get()
                    .body()
                    .text();
            //System.out.println("jsoup获取ip 地址");
        }

        locationMap.put(ip, location);
        history.setLocation(location);

        User user = (User) session.getAttribute("user");
        if (user != null) {
            System.out.println("用户为空!");
            history.setUid(user.getUid());
        }

        historyService.insertHistory(history);

        System.out.println("host=" + host + "&ip=" + ip + "&location=" + location + "&url=" + url + "&uid=");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
