package com.lcpa.lclove.web.controller.admin.article;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcpa.lclove.util.StringUtil;
import com.lcpa.lclove.vo.Paging;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lcpa.lclove.model.Article;
import com.lcpa.lclove.model.ArticleType;
import com.lcpa.lclove.service.ArticleService;
import com.lcpa.lclove.web.controller.AnnotationController;

/**
 * Created by shao on 2016/12/5.
 */
@Controller
public class ArticleController extends AnnotationController{

    @Autowired
    private ArticleService articleService;

	@RequestMapping(value = "/admin/article/test.xhtml")
	public String test(ModelMap model){
		Article article = articleService.getArticleDetailsById(12);
		model.put("article", article);
		return "";
	}

    @RequestMapping(value = "/admin/article/typeList.xhtml")
	public String typeList(ModelMap model){
		List<ArticleType> typeList = articleService.getAllArticleType();
		model.put("typeList", typeList);
		return "admin/article/typeList.vm";
	}

	@RequestMapping(value = "/admin/article/saveType.xhtml")
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
				return showJsonError(model, "该分类不存在或已被删除！");
			} else {
				articleService.updateArticleTypeById(type);
			}
		}
		return "redirect:/admin/article/typeList.xhtml";
	}

    @RequestMapping(value = "/admin/article/delType.xhtml")
	public String delTyle(Integer id, ModelMap model){
    	if(id == null){
    		return showJsonError(model, "ID为空！");
    	}
    	articleService.delArticleTypeById(id);
		return showJsonSuccess(model);
	}

    @RequestMapping(value = "/admin/article/articleList.xhtml")
    public String getArticleList(Integer pageNo,String keyword, Integer type, ModelMap model) {
    	List<ArticleType> typeList = articleService.getAllArticleType();
    	//BeanUtil.beanListMap
    	Map<Integer, ArticleType> typeMap = new HashMap();
    	for (ArticleType articleType : typeList) {
    		typeMap.put(articleType.getId(), articleType);
		}
    	if(pageNo == null){
    		pageNo = 1;
    	}
		Integer pageSize = 100;
		List<Article> articleList = articleService.getAllArticles(pageNo,pageSize,type, keyword);
		Paging paging = articleService.getAllArticlesPaging(pageNo, type, keyword);
		model.put("paging", paging);
		model.put("articleList", articleList);
		model.put("typeMap", typeMap);
		model.put("typeList", typeList);
		model.put("selectType", type);
		return "admin/article/articleList.vm";
    }

    @RequestMapping(value = "/admin/article/editArticle.xhtml")
	public String editArticle(Integer id, ModelMap model){
    	List<ArticleType> typeList = articleService.getAllArticleType();
    	Article article = null;
    	if(id != null){
    		article = articleService.getArticleDetailsById(id);
    	}
    	model.put("article", article);
    	model.put("typeList", typeList);
		return "admin/article/editArticle.vm";
	}

    @RequestMapping(value = "/admin/article/saveArticle.xhtml")
	public String saveArticle(Integer id, ModelMap model) {
    	Article article = null;
		if(id == null){
			article = new Article();
			this.bindParams(article);
			articleService.saveArticle(article);
		}else{
			article = articleService.getArticleDetailsById(id);
			if(article == null){
				return this.showJsonError(model, "该文章不存在或已被删除！");
			}

			this.bindParams(article, new String[]{"id"});
			articleService.updateArticle(article);
		}
    	return "redirect:/admin/article/articleList.xhtml";
    }

    @RequestMapping(value = "/admin/article/delArticle.xhtml")
	public String delArticle(Integer id, ModelMap model){
    	if(id == null){
    		return showJsonError(model, "ID为空！");
    	}
    	articleService.removeArticle(id);
		return showJsonSuccess(model);
	}
}
 