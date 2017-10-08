package com.xing.controller;

import com.xing.entity.Article;
import com.xing.entity.User;
import com.xing.service.impl.ArticleServiceImpl;
import com.xing.service.impl.CategoryServiceImpl;
import com.xing.utils.Page;
import com.xing.utils.PageUtil;
import com.xing.utils.RemoveHTMLTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xing on 2017/9/4.
 */
@Controller
public class HomeController {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private CategoryServiceImpl categoryService;


    @RequestMapping(value = {"/", "/home"})
    public String home() {
        return "forward:/p/1";
    }


    @RequestMapping(value = {"/p/{currentPage}"})
    public String mainPage(@PathVariable("currentPage") Integer currentPage, Model model, HttpSession session) {

        if (currentPage == null) {
            currentPage = 1;
        }


        User user = (User) session.getAttribute("user");

        /*
        *
        *
        * 不登录即访问首页
        * @TODO:
        *
        * */
        if (user == null) {
            user = new User();
            user.setUid(1);
        }

        List<Article> articleList = articleService.listArticleByUid(user.getUid());

        Page page = PageUtil.createPage(5, articleList.size(), currentPage);

        /*
        *
        * 判断页数是否越界
        * */

        if (page.getTotalPage() < currentPage) {
            System.out.println("页面越界了！");
            return "redirect:/err";
        }


        articleList = articleService.listArticleByPage(page.getStartIndex(), page.getEveryPage(), user.getUid());

        /*
        *
        * 去除 HTML 标签
        * */

        articleList.forEach(article -> {
            String html = RemoveHTMLTag.removeTags(article.getHtml());
            if (html.length() > 140) {
                html = html.substring(0, 140);
            }
            article.setHtml(html + " ...... ");
        });


        List<Article> mostView = articleService.viewMost(user.getUid(), 7);

        model.addAttribute("mostView", mostView);
        model.addAttribute("page", page);
        model.addAttribute("articleList", articleList);


        return "front/home";
    }

    @RequestMapping("/err")
    public String error() {
        return "common/error";
    }


    @RequestMapping("/search")
    public String search(@RequestParam(value = "keyword",defaultValue = "") String keyword, Model model, HttpSession session, RedirectAttributes attributes) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setUid(1);
        }

        List<Article> articleList = articleService.search(keyword, user.getUid());


        //String reg = "(?i)" + keyword;
        String reg = keyword;
        articleList.forEach(article -> {
            article.setTitle(article.getTitle().replaceAll(reg, "<mark>" + keyword + "</mark>"));
        });

        model.addAttribute("articleList", articleList);
        model.addAttribute("keyword", keyword);

        return "front/search";
    }


    @RequestMapping("/about")
    public String about() {
        return "front/about";
    }

    @PostMapping("/advice")
    public String advice(String advice, HttpSession session) {

        User user = (User) session.getAttribute("user");

        System.out.println("-----  [" + user.getUsername() + "的意见] ------");
        System.out.println("----- " + advice + " ------");
        System.out.println("-----           -----");

        return "redirect:/about";
    }
}
