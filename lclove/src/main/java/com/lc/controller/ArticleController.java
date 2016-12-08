package com.lc.controller;
import com.lc.model.Article;
import com.lc.service.ArticleService;
import com.lc.service.ArticleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSON;

import java.util.Date;

@Controller
@RequestMapping(value = "/")
/**
 * Created by shao on 2016/12/5.
 */
public class ArticleController {

    @Autowired
    private ArticleServiceI articleService;

    @RequestMapping(value="/test",produces="text/html;charset=UTF-8" )
    @ResponseBody
    private String getOtherList(){
        return null;
    }

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public ModelAndView article() {
        return new ModelAndView("article", "command", new Article());
    }

    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    public String addArticle(@ModelAttribute("SpringWeb")Article article,
                             ModelMap model) {
        article.setArticleType(1);
        article.setLikeNum(0);
        article.setPubDate(new Date());
        article.setScanNum(1);
        articleService.addArticle(article);

        model.addAttribute("title", article.getTitle());
        model.addAttribute("content", article.getContent());

        return "detail";
    }
}
