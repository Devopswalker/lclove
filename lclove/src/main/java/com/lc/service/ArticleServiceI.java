package com.lc.service;

import com.lc.model.Article;

/**
 * Created by shaoheng.huang on 2016/12/7.
 */
public interface ArticleServiceI {
    public Article getFirstArticle();

    public void addArticle(Article article);
}
