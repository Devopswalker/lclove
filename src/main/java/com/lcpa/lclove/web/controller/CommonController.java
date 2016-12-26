package com.lcpa.lclove.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lcpa.lclove.util.WebUtils;

@Controller
public class CommonController extends AnnotationController{
	
	@RequestMapping("/showResult.xhtml")
	public String showResult(){
		return "wide_backPage.vm";
	}
	
	@RequestMapping(value="/admin/login.xhtml",method=RequestMethod.GET)
	public String adminLogin(HttpServletRequest request, ModelMap model){
		String loginSuccessRedirect = request.getSession().getAttribute("loginSuccessRedirect").toString();
		if(StringUtils.isBlank(loginSuccessRedirect)) {
			loginSuccessRedirect = "/admin/index.xhtml";
			request.getSession().setAttribute("loginSuccessRedirect", loginSuccessRedirect);
		}
		model.put("TARGETURL", loginSuccessRedirect);
		model.put("ptn", "user");
		return "admin/login.vm";
	}
	
	@RequestMapping(value = "/admin/index.xhtml")
    public String adminIndex() {
		return "admin/indexPage.vm";
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

	@RequestMapping(value = "/admin/img")
	public String img() {
		return "admin/uploadresult.jsp";
	}

}
