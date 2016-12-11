package com.lcpa.lclove.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CommonController {
	
	@RequestMapping(value = "/admin/index.xhtml")
    public String adminIndex() {
        return "admin/indexPage.vm";
    }
}
