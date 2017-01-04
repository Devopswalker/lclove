package com.lcpa.lclove.web.controller;

import com.lcpa.lclove.model.Article;
import com.lcpa.lclove.model.ArticleType;
import com.lcpa.lclove.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by shaoheng.huang on 2016/12/13.
 */
@Controller
@RequestMapping(value = "/")
public class ControllerTest {
    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/test/getArticleList", method = RequestMethod.GET)
    public String getArticleList() {

        List<ArticleType> articleTypes = articleService.getAllArticleType();


        List<Article> articles = articleService.getArticlesByType(2, 2);

//        List<Article> articles1 = articleService.getHomeArticles(1);

        List<Article> articles2 = articleService.getTopArticlesByType(1);
        return "detail";
    }
}
