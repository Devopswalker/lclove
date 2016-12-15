package com.lcpa.lclove.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CommonController {
	
	@RequestMapping(value = "/admin/index.xhtml")
    public String adminIndex() {
		return "admin/indexPage.vm";
		//return "admin/index.html";
    }
	
	@RequestMapping(value = "/admin/home.xhtml")
    public String homePage() {
        return "admin/homePage.vm";
    }
	
	@RequestMapping(value = "/admin/pass.xhtml")
	public String pass() {
		return "admin/passPage.vm";
	}

    @RequestMapping(value = "/admin/upload")
    public String uploadPage() {
        return "admin/uploadtest.jsp";
    }
}
