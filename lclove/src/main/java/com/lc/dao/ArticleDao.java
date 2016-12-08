package com.lc.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by shaoheng.huang on 2016/12/6.
 */
@Repository("articleDao")
public class ArticleDao {
//    @Autowired
//    private SqlSessionTemplate originalsDBTemplate;

//    public List<Article> findArticle(HashMap map){
//        return originalsDBTemplate.selectList("findArticle",map);
//    }
}
