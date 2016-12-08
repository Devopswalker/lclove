package com.lc.service;

import com.lc.dao.ArticleMapper;
import com.lc.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by shao on 2016/12/5.
 */
@Service
public class ArticleService implements ArticleServiceI {

    @Autowired
    public ArticleMapper articleMapper;

    @Override
    public Article getFirstArticle() {
        return articleMapper.selectByPrimaryKey(1);
    }

    @Override
    public void addArticle(Article article){
        articleMapper.insert(article);
    }
}
