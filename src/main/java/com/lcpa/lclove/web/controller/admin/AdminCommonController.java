package com.lcpa.lclove.web.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lcpa.lclove.model.User;
import com.lcpa.lclove.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lcpa.lclove.web.controller.AnnotationController;

@Controller
public class AdminCommonController extends AnnotationController{

	@Autowired
	public UserService userService;

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

	@RequestMapping(value="/admin/login")
	public String login(HttpSession session, ModelMap model, String username,String password) throws Exception{
		//TODO
		//在Session里保存信息
		session.setAttribute("username", username);
		//重定向
		String loginSuccessRedirect = session.getAttribute("loginSuccessRedirect").toString();
		return "redirect:"+loginSuccessRedirect;
	}

	@RequestMapping(value="/admin/changeUser")
	public String changeUser(ModelMap model, String username,String oldPwd, String newPwd) throws Exception{
		User user = userService.getUserByName(username);
		if (user != null){
			String password = user.getPassword();
			if (password.endsWith(oldPwd)){
				user.setPassword(newPwd);
				userService.updatePassword(user);
			}else {
				return showJsonError(model, "输入旧密码错误！");
			}
		}else {
			return showJsonError(model, "用户："+username + " 不存在！");
		}
		return "redirect:/admin/logout";
	}

	@RequestMapping(value="/admin/logout")
	public String logout(HttpSession session) throws Exception{
		//清除Session
		session.invalidate();
		return "admin/login.vm";
	}

}
