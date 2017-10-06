package com.xing.controller;


import com.xing.entity.Article;
import com.xing.entity.Category;
import com.xing.entity.User;
import com.xing.service.impl.ArticleServiceImpl;
import com.xing.service.impl.CategoryServiceImpl;
import com.xing.utils.Page;
import com.xing.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by xing on 2017/9/9.
 */
@Controller
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    private ArticleServiceImpl articleService;


    @Autowired
    private CategoryServiceImpl categoryService;


    @RequestMapping("/article")
    public String manageArticle(Model model) {
        return "forward:/manage/article/p/1";
    }


    @RequestMapping("/article/p/{currentPage}")
    public String manageArticleByPage(@PathVariable("currentPage") Integer currentPage, Model model, HttpSession session) {
        if (currentPage == null) {
            currentPage = 1;
        }

        User user = (User) session.getAttribute("user");

        List<Article> articleList = articleService.listArticleByUid(user.getUid());

        Page page = PageUtil.createPage(7, articleList.size(), currentPage);
        if (page.getTotalPage() < currentPage) {
            System.out.println("页面越界了！");
            return "redirect:/err";
        }

        articleList = articleService.listArticleByPage(page.getStartIndex(), page.getEveryPage(), user.getUid());
        model.addAttribute("articleList", articleList);
        model.addAttribute("page", page);

        return "manage/articleManagement";
    }

    @RequestMapping("/category")
    public String manageCategory(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");

        List<Category> categoryList = categoryService.listCategoryByUid(user.getUid());
        model.addAttribute("categoryList", categoryList);

        return "manage/categoryManagement";
    }

}
