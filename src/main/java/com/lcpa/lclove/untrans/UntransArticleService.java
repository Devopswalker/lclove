package com.lcpa.lclove.untrans;

import com.lcpa.lclove.dao.ArticleMapper;
import com.lcpa.lclove.dao.ArticleTypeMapper;
import com.lcpa.lclove.model.Article;
import com.lcpa.lclove.model.ArticleType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by shaoheng.huang on 2016/12/14.
 */
public class UntransArticleService {
    @Autowired
    public ArticleMapper articleMapper;

    @Autowired
    public ArticleTypeMapper articleTypeMapper;

    public List<ArticleType> getAllArticleType(){
        return articleTypeMapper.selectAll();
    }

    public List<Article> getArticlesByType(Integer articleType, Integer pageNo){
        Integer startIndex = 0;
        Integer pageSize = pageNo*2;
        return articleMapper.selectArticleByType(articleType, startIndex, pageSize);
    }

//    public List<Article> getHomeArticles(Integer pageNo){
//        Integer startIndex = 0;
//        Integer pageSize = pageNo*2;
//        return articleMapper.selectHomeArticle(startIndex, pageSize);
//    }

//    public List<Article> getTopArticlesByType(Integer articleType){
//        Integer startIndex = 0;
//        Integer pageSize = 3;
//        return articleMapper.selectTopArticlesByType(articleType, startIndex, pageSize);
//    }
}
