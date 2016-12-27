package com.lcpa.lclove.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LcLoveAjaxController extends AnnotationController{
	
	
	/*获取图文推荐*/
	@RequestMapping("/ajax/getRecommand.xhtml")
	public String getRecommand(ModelMap model){
		return showJsonSuccess(model, "");
	}

}
