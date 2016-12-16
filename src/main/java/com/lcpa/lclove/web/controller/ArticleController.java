package com.lcpa.lclove.web.controller;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lcpa.lclove.model.Article;
import com.lcpa.lclove.model.ArticleType;
import com.lcpa.lclove.service.ArticleService;
import com.lcpa.lclove.util.WebUtils;

/**
 * Created by shao on 2016/12/5.
 */
@Controller
public class ArticleController extends AnnotationController{

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
    
    @RequestMapping(value = "/admin/typeList.xhtml")
	public String typeList(ModelMap model){
		List<ArticleType> typeList = articleService.getAllArticleType();
		model.put("typeList", typeList);
		return "admin/typeList.vm";
	}
    
	@RequestMapping(value = "/admin/saveType.xhtml")
	public String saveType(ArticleType type, ModelMap model) {
		//ArticleType type = WebUtils.bindReqParams(request, ArticleType.class);
		if (type == null) {
			return showJsonError(model, "参数错误！");
		}
		if (StringUtils.isBlank(type.getName())) {
			return showJsonError(model, "分类名称为空！");
		}
		if (type.getId() == null) {
			articleService.saveArticleType(type);
		} else {
			ArticleType old = articleService.getArticleTypeById(type.getId());
			if (old == null) {
				return showJsonError(model, "分类不存在或已删除！");
			} else {
				articleService.updateArticleTypeById(type);
			}
		}
		return "redirect:/admin/typeList.xhtml";
	}
    
    @RequestMapping(value = "/admin/delType.xhtml")
	public String delTyle(Integer id, ModelMap model){
    	if(id == null){
    		return showJsonError(model, "ID为空！");
    	}
    	articleService.delArticleTypeById(id);
		return showJsonSuccess(model);
	}
    
}
