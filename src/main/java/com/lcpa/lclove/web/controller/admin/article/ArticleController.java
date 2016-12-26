package com.lcpa.lclove.web.controller.admin.article;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Article article = articleService.getArticleDetails(12);
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
			return showJsonError(model, "�������");
		}
		if (StringUtils.isBlank(type.getName())) {
			return showJsonError(model, "�������Ϊ�գ�");
		}
		if (type.getId() == null) {
			articleService.saveArticleType(type);
		} else {
			ArticleType old = articleService.getArticleTypeById(type.getId());
			if (old == null) {
				return showJsonError(model, "���಻���ڻ���ɾ��");
			} else {
				articleService.updateArticleTypeById(type);
			}
		}
		return "redirect:/admin/article/typeList.xhtml";
	}
    
    @RequestMapping(value = "/admin/article/delType.xhtml")
	public String delTyle(Integer id, ModelMap model){
    	if(id == null){
    		return showJsonError(model, "IDΪ�գ�");
    	}
    	articleService.delArticleTypeById(id);
		return showJsonSuccess(model);
	}
    
    @RequestMapping(value = "/admin/article/articleList.xhtml")
    public String getArticleList(Integer pageNo, String s_tohome, String s_torecom, String keyword, Integer type, ModelMap model) {
    	List<ArticleType> typeList = articleService.getAllArticleType();
    	//BeanUtil.beanListMap
    	Map<Integer, ArticleType> typeMap = new HashMap();
    	for (ArticleType articleType : typeList) {
    		typeMap.put(articleType.getId(), articleType);
		}
    	if(pageNo == null){
    		pageNo = 1;
    	}
    		
    	List<Article> articleList = articleService.getHomeArticles(pageNo);
    	model.put("typeMap", typeMap);
    	model.put("typeList", typeList);
    	model.put("articleList", articleList);
        return "admin/article/articleList.vm";
    }
    
    @RequestMapping(value = "/admin/article/editArticle.xhtml")
	public String editArticle(Integer id, ModelMap model){
    	List<ArticleType> typeList = articleService.getAllArticleType();
    	Article article = null;
    	if(id != null){
    		article = articleService.getArticleById(id);
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
			article = articleService.getArticleById(id);
			if(article == null){
				return this.showJsonError(model, "���²�����!");
			}
			
			this.bindParams(article, new String[]{"id"});
			articleService.updateArticle(article);
		}
    	return "redirect:/admin/article/articleList.xhtml";
    }
    
    @RequestMapping(value = "/admin/article/delArticle.xhtml")
	public String delArticle(Integer id, ModelMap model){
    	if(id == null){
    		return showJsonError(model, "IDΪ�գ�");
    	}
    	articleService.delArticleById(id);
		return showJsonSuccess(model);
	}
}
 