package com.lcpa.lclove.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lcpa.lclove.constant.CommConstant;
import com.lcpa.lclove.model.Article;
import com.lcpa.lclove.model.ImageRecommend;
import com.lcpa.lclove.service.ArticleService;
import com.lcpa.lclove.service.RecommendService;
import com.lcpa.lclove.util.JsonUtils;
import com.lcpa.lclove.vo.PagingJsonVo;


@Controller
public class LcLoveAjaxController extends AnnotationController{
	
	@Autowired
    private ArticleService articleService;
	
	@Autowired
	private RecommendService recommendService;
	
	/**
	 * Get Recommand dataList
	 * @param position(ref CommConstant.REC_TYPE_XX)
	 * @param model
	 * @return
	 */
	@RequestMapping("/ajax/getRecommand.xhtml")
	public String getRecommandList(Integer position, ModelMap model){
		if(position == null){
			return showJsonError(model, "Position cant be empty!");
		}
		int pageSize = 3;
		if(position == CommConstant.REC_TYPE_SLID){
			pageSize = 5;
		}
		List<ImageRecommend> recommendList = recommendService.getRecommendImagesByPosition(position, pageSize);
		return showJsonSuccess(model, JsonUtils.writeObjectToJson(recommendList));
	}
	
	/**
	 * Get Article dataList
	 * @param type
	 * @param pageNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/ajax/getArticleList.xhtml")
	public String getArticleList(Integer type, Integer pageNo, ModelMap model){
		if(type == null){
			type = 1;
		}
		if(pageNo == null){
			pageNo = 1;
		}
		Integer rowsPerPage = 5;
		List<Article> articleList = new ArrayList<Article>(0);
		//TODO:分页
		if(type == 1){
			articleList = articleService.getHomeArticles(pageNo);
		}else{
			articleList = articleService.getArticlesByType(type, pageNo);
		}
		PagingJsonVo page = new PagingJsonVo(articleList.size(), rowsPerPage, pageNo);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("articles", articleList);
		resultMap.put("pageInfo", page);
		return showJsonSuccess(model, JsonUtils.writeObjectToJson(resultMap));
	}
	
	/**
	 * Get Article Details
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/ajax/getArticle.xhtml")
	public String getArticleDetail(Integer id, ModelMap model){
		if(id == null){
			return showJsonError(model, "资源未开放浏览");
		}
		Article article = articleService.getArticleById(id);
		if(article == null){
			return showJsonError(model, "资源未开放浏览");
		}
		return showJsonSuccess(model, JsonUtils.writeObjectToJson(article));
	}

}
