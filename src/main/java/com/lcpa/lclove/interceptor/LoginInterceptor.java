package com.lcpa.lclove.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * Created by shao on 2016/12/25.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
        //URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
        if(url.indexOf("login")>=0){
            return true;
        }
        //获取Session
        HttpSession session = httpServletRequest.getSession();
        String username = (String)session.getAttribute("username");

        if(username != null){
            return true;
        }
        //不符合条件的，跳转到登录界面 TARGETURL
        //httpServletResponse.sendRedirect("/adminLogin.xhtml");
        httpServletResponse.sendRedirect("/Lclove/admin/login.xhtml?TARGETURL=" + URLEncoder.encode(url));
        //httpServletRequest.getRequestDispatcher("/WEB-INF/page/admin/login.vm").forward(httpServletRequest, httpServletResponse);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
