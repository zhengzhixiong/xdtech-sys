package com.xdtech.sys.util;

import org.apache.shiro.SecurityUtils;

import com.xdtech.sys.model.User;

public class SessionContextUtil {
	
	public static User getCurrentUser() {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		return currentUser;
	}

}
