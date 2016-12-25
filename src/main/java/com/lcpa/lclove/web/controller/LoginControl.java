package com.lcpa.lclove.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shao on 2016/12/25.
 */
@Controller
public class LoginControl {

    @RequestMapping(value="/admin/login.action")
    public String login(HttpSession session, ModelMap model, String username,String password, String TARGETURL) throws Exception{
        //在Session里保存信息
        session.setAttribute("username", username);
        //重定向

        return "redirect:"+TARGETURL;
    }

    @RequestMapping(value="/logout")
    public String logout(HttpSession session) throws Exception{
        //清除Session
        session.invalidate();

        return "redirect:hello.action";
    }
}
