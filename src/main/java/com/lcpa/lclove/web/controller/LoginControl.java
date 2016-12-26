package com.lcpa.lclove.web.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shao on 2016/12/25.
 */
@Controller
public class LoginControl {

    @RequestMapping(value="/admin/login.action")
    public String login(HttpSession session, ModelMap model, String username,String password, String targetUrl) throws Exception{
        //TODO:用户登录检查
    	/**
    	 * ...
    	 */
    	//在Session里保存信息
        session.setAttribute("username", username);
        if(StringUtils.isNotBlank(targetUrl)){
        	//TODO:查看是否需要转码
        	return "redirect:"+targetUrl;
        }
        return "redirect:/admin/index.xhtml";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpSession session) throws Exception{
        //清除Session
        session.invalidate();

        return "redirect:hello.action";
    }
}
