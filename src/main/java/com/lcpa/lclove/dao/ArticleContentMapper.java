package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.Article;
import com.lcpa.lclove.model.ArticleContent;

public interface ArticleContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleContent record);

    int insertArticle(Article record);

    int insertSelective(ArticleContent record);

    ArticleContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleContent record);

    int updateArticleByPrimaryKeyWithBLOBs(Article record);
}