package cn._51app.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Administrator
 * 登录
 */
@Controller
public class LoginController {
	
	/**
	 * tengh 2017年1月16日 下午2:38:15
	 * @return
	 * TODO 跳转登录的页面
	 */
	@RequestMapping(value = "/login",method=RequestMethod.GET)
    public String showLoginForm() {
		return "login";
	}
	
	/**
	 * tengh 2017年1月16日 下午2:38:06
	 * @param request
	 * @param response
	 * @return
	 * TODO 登出
	 */
	@RequestMapping(value = "/logout",method=RequestMethod.GET)
    public String loginout(HttpServletRequest request,HttpServletResponse response) {
		Subject subject=SecurityUtils.getSubject();
		subject.logout();
		//清除对应的缓存
		return "redirect:/login";
	}
	
    /**
     * tengh 2017年1月16日 下午2:37:53
     * @param req
     * @param model
     * @return
     * TODO 登录验证
     */
    @RequestMapping(value = "/login",method=RequestMethod.POST)
    public String showLoginForm(HttpServletRequest req, Model model) {
    	String username = req.getParameter("username");  
        String password = req.getParameter("password");
        boolean rememberMe=Boolean.valueOf(req.getParameter("rememberMe"));
        Subject subject = SecurityUtils.getSubject();  
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        String error="";
        try {
        	if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
        		token.setRememberMe(rememberMe);
        		subject.login(token);
        	}else{
        		error="请输入正确账号";
        	}
		}catch (UnknownAccountException unknownAccountException) {
			error = "用户名错误";
		}catch (IncorrectCredentialsException unknownAccountException) {
			error = "密码错误";
		}catch(ExcessiveAttemptsException excessiveAttemptsException){
			error = "登录失败次数过多,请十分钟后重试!";
		}catch (Exception e) {
			e.printStackTrace();
			error = "异常错误";
		} 
        if(StringUtils.isBlank(error)){
        	return "redirect:/index";
        }else{
        	model.addAttribute("error", error);
        	model.addAttribute("username", username);
        	return "login";
        }
    }
    
}
