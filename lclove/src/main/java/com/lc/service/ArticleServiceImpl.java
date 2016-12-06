package com.lc.service;

import com.lc.dao.ArticleDao;
import com.lc.pojo.Article;
import com.lc.serviceInterface.ArticleServiceI;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * Created by shao on 2016/12/5.
 */
@Service("articleServiceImpl")
public class ArticleServiceImpl{

    @Autowired
    public ArticleDao articleDao;

    public List<Article> getAllArticle() {
        return articleDao.findArticle(new HashMap());
    }
}
