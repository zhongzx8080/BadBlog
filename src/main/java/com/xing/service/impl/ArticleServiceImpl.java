package com.xing.service.impl;

import com.xing.entity.Article;
import com.xing.mapper.ArticleMapper;
import com.xing.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by xing on 2017/9/6.
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int insertArticle(Article article) {
        return articleMapper.insertArticle(article);
    }

    @Override
    public Article getArticleByAid(Integer aid) {
        return articleMapper.getArticleByAid(aid);
    }


    @Override
    public List<Article> listArticleByUid(Integer uid) {
        return articleMapper.listArticleByUid(uid);
    }


    @Override
    public List<Article> listArticleByAidList(List<Integer> aidList) {
        return articleMapper.listArticleByAidList(aidList);
    }

    @Override
    public void updateView(Integer aid, Integer times) {
        articleMapper.updateView(aid, times);
    }

    @Override
    public void updateArticle(Integer aid, String title, String markdown, String html) {
        articleMapper.updateArticle(aid, title, markdown, html);
    }

    @Override
    public List<Article> search(String keyword, Integer uid) {
        return articleMapper.search(keyword,uid);
    }

    @Override
    public void deleteArticle(List<Integer> aidList,Integer uid) {
        articleMapper.deleteArticle(aidList,uid);
    }


    @Override
    public List<Article> listArticleByPage(Integer startIndex, Integer count, Integer uid) {
        return articleMapper.listArticleByPage(startIndex, count, uid);
    }


    @Override
    public List<Article> viewMost(Integer uid, Integer count) {
        return articleMapper.viewMost(uid, count);
    }
}
