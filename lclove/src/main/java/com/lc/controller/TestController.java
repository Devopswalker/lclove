package com.lc.controller;
import com.lc.pojo.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
/**
 * Created by shao on 2016/12/5.
 */
@Controller
public class TestController {
    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public ModelAndView article() {
        return new ModelAndView("article", "command", new Article());
    }

    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    public String addArticle(@ModelAttribute("SpringWeb")Article article,
                             ModelMap model) {
        model.addAttribute("name", article.getName());
        model.addAttribute("content", article.getContent());

        return "detail";
    }

}
