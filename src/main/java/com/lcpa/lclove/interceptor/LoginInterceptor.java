package com.lcpa.lclove.interceptor;

import com.lcpa.lclove.util.WebUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by shao on 2016/12/25.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    public static final String LAST_MODEL_VIEW_ATTRIBUTE = LoginInterceptor.class.getName() + ".lastModelAndView";
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getServletPath();
        if(url.indexOf("login")>=0){
            return true;
        }
        //获取Session
        HttpSession session = httpServletRequest.getSession();
        String username = (String)session.getAttribute("username");

        if(username != null){
            return true;
        }
        //不符合条件的，跳转到登录界面
//        httpServletRequest.getSession().setAttribute("loginSuccessRedirect", url);
        String redirectUrl = WebUtils.getContextPath(httpServletRequest) + "admin/login.xhtml";
        httpServletResponse.sendRedirect(redirectUrl);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
