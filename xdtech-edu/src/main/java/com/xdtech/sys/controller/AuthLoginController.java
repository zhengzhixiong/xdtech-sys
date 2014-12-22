package com.xdtech.sys.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.xdtech.web.model.ResultMessage;

@Controller
@Scope("prototype")
@RequestMapping("/authLogin.action")
public class AuthLoginController {

	@RequestMapping(params = "login")
	@ResponseBody
	public ResultMessage login(String loginName, String password, Boolean remember,HttpServletRequest request) {
		AuthenticationToken token = new UsernamePasswordToken(loginName,
				password, remember);
		ResultMessage rm = new ResultMessage();
		try {
			SecurityUtils.getSubject().login(token);
		}catch (UnknownAccountException e) {
			rm.setSuccess(false);
			rm.setMsg("用户不存在");
		} catch (IncorrectCredentialsException e) {
			rm.setSuccess(false);
			rm.setMsg("密码错误");
		} catch (Exception e) {
			e.printStackTrace();
			rm.setSuccess(false);
			rm.setMsg("用户名或密码有误，登录失败");
		}
		setLanguage(request);
		return rm;
	}
	
	private void setLanguage(HttpServletRequest request) {
		String language = request.getParameter("language");
		if (language!=null) {
			Locale locale = null;
			if (language.equals("zh_CN")) {
				locale = new Locale("zh","CN");
			}else if (language.equals("en_US")) {
				locale = new Locale("en","US");
			}else if (language.equals("ja_JP")) {
				locale = new Locale("ja","CN");
			}else {
				locale = new Locale("zh","CN");
			}
			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
		}
	}
	
	@RequestMapping(params = "logout")
	@ResponseBody
	public ResultMessage logout() {
		SecurityUtils.getSubject().logout();
		return new ResultMessage();
	}

}
