package com.xing.service;

import com.xing.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xing on 2017/9/6.
 */
@Service
public interface IArticleService {
    int insertArticle(Article article);

    Article getArticleByAid(Integer aid);

    List<Article> listArticleByUid(Integer uid);

    List<Article> listArticleByAidList(List<Integer> aidList);

    void updateView(Integer aid, Integer times);

    void updateArticle(Integer aid, String title, String markdown, String html);

    void deleteArticle(List<Integer> aidList,Integer uid);

    List<Article> listArticleByPage(Integer startIndex, Integer count, Integer uid);

    List<Article> viewMost(Integer uid, Integer count);

    List<Article> search(String keyword, Integer uid);
}
