package com.lcpa.lclove.dao;

import com.lcpa.lclove.model.Article;
import com.lcpa.lclove.model.ArticleType;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    List<Article> selectAllArticles(Integer startIndex, Integer pageSize);

    List<Article> selectHomeArticle(Integer startIndex, Integer pageSize);

    List<Article> selectArticleByType(Integer articleType, Integer startIndex, Integer pageSize);

    List<Article> selectTopArticlesByType(Integer articleType, Integer startIndex, Integer pageSize);

    List<Article> selectArticlesByKeyWords(String searchText, Integer startIndex, Integer pageSize);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
}