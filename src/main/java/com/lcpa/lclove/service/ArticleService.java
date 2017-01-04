package com.lcpa.lclove.service;

import com.lcpa.lclove.dao.ArticleContentMapper;
import com.lcpa.lclove.dao.ArticleMapper;
import com.lcpa.lclove.dao.ArticleTypeMapper;
import com.lcpa.lclove.model.Article;

import com.lcpa.lclove.model.ArticleContent;
import com.lcpa.lclove.model.ArticleType;
import com.lcpa.lclove.model.ImageRecommend;
import com.lcpa.lclove.vo.Paging;
import com.lcpa.lclove.vo.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * admin 保存文章
     * @param article
     */
    public void saveArticle(Article article){
        articleMapper.insert(article);
        articleContentMapper.insertArticle(article);
    }

    /**
     * admin 更新文章
     * @param article
     */
    public void updateArticle(Article article){
        articleMapper.updateByPrimaryKey(article);
        articleContentMapper.updateByPrimaryKeyWithBLOBs(article);
    }

    /**
     * admin 删除文章
     * @param id
     */
    public  void removeArticle(Integer id){
        articleMapper.deleteByPrimaryKey(id);
        articleContentMapper.deleteByPrimaryKey(id);
    }

    /**
     * admin 更新文章
     * @param article
     */
    public void updateArticleById(Article article){
    	articleMapper.updateByPrimaryKey(article);
    }

    /**
     * admin 获取文章信息
     * @param id
     * @return
     */
    public Article getArticleById(Integer id){
    	return articleMapper.selectByPrimaryKey(id);
    }

    /**
     * admin 获取文章信息以及文章内容
     * @param id
     * @return
     */
    public Article getArticleDetailsById(Integer id){
        Article article = articleMapper.selectByPrimaryKey(id);
        ArticleContent articleContent = articleContentMapper.selectByPrimaryKey(id);
        article.setContent(articleContent.getContent());
        return article;
    }

    /**
     * admin 保存文章类型
     * @param type
     */
    public void saveArticleType(ArticleType type){
    	articleTypeMapper.insertSelective(type);
    }

    /**
     * admin 删除文章类型
     * @param id
     */
    public void delArticleTypeById(Integer id){
    	articleTypeMapper.deleteByPrimaryKey(id);
    }

    /**
     * admin 更新文章分类
     * @param record
     */
    public void updateArticleTypeById(ArticleType record){
    	articleTypeMapper.updateByPrimaryKey(record);
    }

    /**
     * admin获取文章分类
     * @param id
     * @return
     */
    public ArticleType getArticleTypeById(Integer id){
    	return articleTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * admin 获取所有文章类型
     * @return
     */
    public List<ArticleType> getAllArticleType(){
        return articleTypeMapper.selectAll();
    }

    /**
     *
     * @param pageNo 第几页
     * @param pageSize 每页大小
     * @param articleType 1：Spacial，2：Love，3：Body， null：所有（home页面）
     * @param keywords 查找关键字
     * @return
     */
    public List<Article> getAllArticles(Integer pageNo, Integer pageSize,Integer articleType, String keywords){
        Map map = new HashMap<>();
        if (articleType != null){
            map.put("articleType", articleType);
        }
        if (keywords != null && !keywords.equals("")){
            map.put("keywords", keywords);
        }
        Paging paging = new Paging(pageNo, pageSize);
        QueryParameter queryParameter = new QueryParameter(paging,map);
        return articleMapper.selectAllArticles(queryParameter);
    }

    /**
     * admin获取文章分页信息 //TODO 可更改
     * @param pageNo
     * @param articleType
     * @param keywords
     * @return
     */
    public Paging getAllArticlesPaging(Integer pageNo, Integer articleType, String keywords){
        Map map = new HashMap<>();
        if (articleType != null){
            map.put("articleType", articleType);
        }
        if (keywords != null && !keywords.equals("")){
            map.put("keywords", keywords);
        }
        QueryParameter queryParameter = new QueryParameter(null,map);
        List<Article> articles = articleMapper.selectAllArticles(queryParameter);
        int total = articles.size();
        Paging paging = new Paging(pageNo, 100);
        paging.setTotal(total);
        return paging;
    }

    /**
     * 获取文章内容信息
     * @param article
     * @return
     */
    public Article getArticleContent(Article article){
        ArticleContent articleContent = articleContentMapper.selectByPrimaryKey(article.getId());
        article.setContent(articleContent.getContent());
        return article;
    }

    /**
     * 获取文章top列表
     * @param articleType  1：Spacial，2：Love，3：Body， null：所有（home页面）
     * @return
     */
    public List<Article> getTopRankArticlesByType(Integer articleType, Integer size){
        Integer startIndex = 0;
        Integer pageSize = size;
        return articleMapper.selectTopArticlesByType(articleType, startIndex, pageSize);
    }

}
