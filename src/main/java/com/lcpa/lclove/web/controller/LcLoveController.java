package com.lcpa.lclove.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LcLoveController extends AnnotationController{
	
	
	/*恋の首页*/
	@RequestMapping("/index")
	public String initIndex(){
		return "lclove/index.vm";
	}
	
	/*恋の喵言喵语*/
	@RequestMapping("/special.xhtml")
	public String initSpecial(){
		return "lclove/special.vm";
	}
	
	/*恋の恋爱经*/
	@RequestMapping("/loves.xhtml")
	public String initLoves(){
		return "lclove/loves.vm";
	}
	
	/*恋の护身符*/
	@RequestMapping("/amulet.xhtml")
	public String initAmulet(){
		return "lclove/amulet.vm";
	}
	
	/*恋の好奇心&列表*/
	@RequestMapping("/research.xhtml")
	public String initResearch(){
		return "lclove/research.vm";
	}
	
	/*恋の好奇心&详情*/
	@RequestMapping("/researchDetail.xhtml")
	public String initResearchDetail(){
		return "lclove/researchDetail.vm";
	}
	
	/*恋の好奇心&结果详情*/
	@RequestMapping("/researchResult.xhtml")
	public String initresearchResult(){
		return "lclove/researchResult.vm";
	}
	
	/*恋の梦剧场*/
	@RequestMapping("/comic.xhtml")
	public String initComic(){
		return "lclove/comic.vm";
	}
	
	/*恋の上上签*/
	@RequestMapping("/pray.xhtml")
	public String initPray(){
		return "lclove/pray.vm";
	}
	
	/*文章详情*/
	@RequestMapping("/detail.xhtml")
	public String initDetail(){
		return "lclove/detail.vm";
	}
	
	/*关于LM*/
	@RequestMapping("/lianmao.xhtml")
	public String initLianmao(){
		return "lclove/lianmao.vm";
	}
	
	/*关于LC*/
	@RequestMapping("/brand.xhtml")
	public String initBrand(){
		return "lclove/brand.vm";
	}

}
