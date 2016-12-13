package com.lcpa.lclove.web.controller;
import com.lcpa.lclove.model.Article;
import com.lcpa.lclove.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import java.util.Date;

@Controller
@RequestMapping(value = "/")
/**
 * Created by shao on 2016/12/5.
 */
public class ArticleController {

    @Autowired
    private ArticleService articleService;

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
        article.setPubDate(new Date());
        articleService.saveArticle(article);
        return "detail";
    }

    @RequestMapping(value = "/getArticleList", method = RequestMethod.GET)
    public String getArticleList(@ModelAttribute("SpringWeb")Integer pageNo,
                             ModelMap model) {


        return "detail";
    }
}
