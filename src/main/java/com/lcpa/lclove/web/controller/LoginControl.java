package com.lcpa.lclove.web.controller;

import javax.servlet.http.HttpSession;

import com.lcpa.lclove.interceptor.LoginInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by shao on 2016/12/25.
 */
@Controller
public class LoginControl {

    @RequestMapping(value="/admin/login.action")
    public String login(HttpSession session, String username,String password) throws Exception{
        //在Session里保存信息
        session.setAttribute("username", username);
        //重定向
        String targetUrl = session.getAttribute("loginSuccessRedirect").toString();
        return "redirect:"+targetUrl;

    }

    @RequestMapping(value="/logout")
    public String logout(HttpSession session) throws Exception{
        //清除Session
        session.invalidate();
        return "redirect:/admin/login.xhtml";
    }
}
