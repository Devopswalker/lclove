package com.lcpa.lclove.dao;

import java.util.List;

import com.lcpa.lclove.model.Article;
import com.lcpa.lclove.vo.QueryParameter;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    List<Article> selectAllArticles(QueryParameter queryParameter);

    List<Article> selectHomeArticle(QueryParameter queryParameter);

    List<Article> selectArticleByType(Integer articleType, Integer startIndex, Integer pageSize);

    List<Article> selectTopArticlesByType(QueryParameter queryParameter);

    List<Article> selectArticlesByKeyWords(String searchText, Integer startIndex, Integer pageSize);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    void updateScanNum(Integer id);

    List<Article> selectLastByPubDate(Article currentArticle);

    List<Article> selectLastByScanNum(Article currentArticle);

    List<Article> selectNextByPubDate(Article currentArticle);

    List<Article> selectNextByScanNum(Article currentArticle);
}