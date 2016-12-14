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
	
	@RequestMapping(value = "/admin/info.xhtml")
    public String homePage() {
        return "admin/info.html";
    }

    @RequestMapping(value = "/admin/upload")
    public String uploadPage() {
        return "admin/uploadtest.jsp";
    }

}
