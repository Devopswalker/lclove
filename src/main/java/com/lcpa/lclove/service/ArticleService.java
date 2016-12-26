package com.lcpa.lclove.service;

import com.lcpa.lclove.dao.ArticleContentMapper;
import com.lcpa.lclove.dao.ArticleMapper;
import com.lcpa.lclove.dao.ArticleTypeMapper;
import com.lcpa.lclove.model.Article;

import com.lcpa.lclove.model.ArticleContent;
import com.lcpa.lclove.model.ArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shao on 2016/12/5.
 */
@Service
public class ArticleService{

    @Autowired
    public ArticleMapper articleMapper;

    @Autowired
    public ArticleContentMapper articleContentMapper;

    @Autowired
    public ArticleTypeMapper articleTypeMapper;

    public void saveArticle(Article article){
        articleMapper.insert(article);
        articleContentMapper.insertArticle(article);
    }

    public void updateArticle(Article article){
        articleMapper.updateByPrimaryKey(article);
        articleContentMapper.updateByPrimaryKeyWithBLOBs(article);
    }
    public  void removeArticle(Integer id){
        articleMapper.deleteByPrimaryKey(id);
        articleContentMapper.deleteByPrimaryKey(id);
    }
    
    public void delArticleById(Integer id){
    	articleMapper.deleteByPrimaryKey(id);
    }
    
    public void updateArticleById(Article article){
    	articleMapper.updateByPrimaryKey(article);
    }
    
    public Article getArticleById(Integer id){
    	return articleMapper.selectByPrimaryKey(id);
    }

    public Article getArticleDetailsById(Integer id){
        Article article = articleMapper.selectByPrimaryKey(id);
        ArticleContent articleContent = articleContentMapper.selectByPrimaryKey(id);
        article.setContent(articleContent.getContent());
        return article;
    }
    
    public void saveArticleType(ArticleType type){
    	articleTypeMapper.insertSelective(type);
    }
    
    public void delArticleTypeById(Integer id){
    	articleTypeMapper.deleteByPrimaryKey(id);
    }
    
    public void updateArticleTypeById(ArticleType record){
    	articleTypeMapper.updateByPrimaryKey(record);
    }
    
    public ArticleType getArticleTypeById(Integer id){
    	return articleTypeMapper.selectByPrimaryKey(id);
    }

    public List<ArticleType> getAllArticleType(){
        return articleTypeMapper.selectAll();
    }

    public List<Article> getArticlesByType(Integer articleType, Integer pageNo){
        Integer startIndex = 0;
        Integer pageSize = pageNo*2;
        return articleMapper.selectArticleByType(articleType, startIndex, pageSize);
    }

    public List<Article> getHomeArticles(Integer pageNo){
        Integer startIndex = 0;
        Integer pageSize = pageNo*2;
        return articleMapper.selectHomeArticle(startIndex, pageSize);
    }

    public Article getArticleContent(Article article){
        ArticleContent articleContent = articleContentMapper.selectByPrimaryKey(article.getId());
        article.setContent(articleContent.getContent());
        return article;
    }

    public List<Article> getTopArticlesByType(Integer articleType){
        Integer startIndex = 0;
        Integer pageSize = 3;
        return articleMapper.selectTopArticlesByType(articleType, startIndex, pageSize);
    }

    public List<Article> getArticlesByKeyWords(String searchText){
        Integer startIndex = 0;
        Integer pageSize = 3;
        searchText = "%"+searchText+"%";
        return articleMapper.selectArticlesByKeyWords(searchText, startIndex, pageSize);
    }
}
