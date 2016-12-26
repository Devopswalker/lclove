package com.lcpa.lclove.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController extends AnnotationController{
	
	@RequestMapping("/showResult.xhtml")
	public String showResult(){
		return "wide_backPage.vm";
	}

	@RequestMapping(value = "/admin/img")
	public String img() {
		return "admin/uploadresult.jsp";
	}

}
