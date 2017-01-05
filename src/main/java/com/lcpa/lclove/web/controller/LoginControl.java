//package com.lcpa.lclove.web.controller;
//
//import javax.servlet.http.HttpSession;
//
//import com.lcpa.lclove.model.User;
//import com.lcpa.lclove.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// * Created by shao on 2016/12/25.
// */
//@Controller
//public class LoginControl extends AnnotationController{
//
//    @Autowired
//    public UserService userService;
//
//    @RequestMapping(value="/admin/login.action")
//    public String login(HttpSession session, ModelMap model, String username,String password) throws Exception{
//        //TODO
//        //在Session里保存信息
//        User user = userService.getUserByName(username);
//        if (user.getPassword().equals(password)){
//            session.setAttribute("username", username);
//            //重定向
//            String loginSuccessRedirect = "/admin/index.xhtml";
//            return "redirect:"+loginSuccessRedirect;
//        }else{
//            return showJsonError(model, "账号密码错误！");
//        }
//    }
//
////    @RequestMapping(value="/admin/changeUser")
////    public String changeUser(HttpSession session, ModelMap model, String username,String password) throws Exception{
////        //TODO
////        //在Session里保存信息
////        session.setAttribute("username", username);
////        //重定向
////        String loginSuccessRedirect = session.getAttribute("loginSuccessRedirect").toString();
////        return "redirect:"+loginSuccessRedirect;
////    }
//
////    @RequestMapping(value="/admin/logout")
////    public String logout(HttpSession session) throws Exception{
////        //清除Session
////        session.invalidate();
////
////        return "redirect:hello.action";
////    }
//}
