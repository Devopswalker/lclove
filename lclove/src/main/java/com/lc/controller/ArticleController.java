package com.lc.controller;
import com.lc.pojo.Article;
import com.lc.serviceInterface.ArticleServiceI;
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

    @RequestMapping(value = "/showArticle", method = RequestMethod.POST)
    public String addArticle(@ModelAttribute("SpringWeb")Article article,
                             ModelMap model) {
        model.addAttribute("name", article.getName());
        model.addAttribute("content", article.getContent());

        return "detail";
    }
}
