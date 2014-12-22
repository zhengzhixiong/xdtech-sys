package com.xdtech.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.xdtech.common.utils.EmptyUtil;
import com.xdtech.common.utils.EncryptUtil;
import com.xdtech.sys.model.MenuFunction;
import com.xdtech.sys.model.User;
import com.xdtech.sys.vo.RoleItem;

public class AuthRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
		// 如果是超级管理员，则拥有所有权限 modify by
		User currentUser = (User) SecurityUtils.getSubject().getSession()
				.getAttribute("user");

		List<Long> roleIds = new ArrayList<Long>();
		List<MenuFunction> menuFunctions = null;

		Long userId = currentUser.getId();
		List<RoleItem> roles = userService.loadUserRoles(userId);
		for (RoleItem role : roles) {
			authInfo.addRole(role.getCode());
			roleIds.add(role.getId());
			// 如果是超级管理员的话
			if (role.isSuperuser()) {
				// 拥有所有权限
				authInfo.addStringPermission("*");
				return authInfo;
			}
		}
		menuFunctions = userService.loadUserFunctions(userId, roleIds);

		if (EmptyUtil.isNotEmpty(menuFunctions)) {
			for (MenuFunction mf : menuFunctions) {
				if (StringUtils.isNotEmpty(mf.getOperationCode())) {
					authInfo.addStringPermission(mf.getOperationCode());
				}
			}
		}
		return authInfo;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;

		String loginName = (String) userToken.getPrincipal(); // 得到用户名
		String password = String.valueOf(userToken.getPassword()); // 得到密码
		User currentUser = userService.getUserByName(loginName);
		if (currentUser == null
				|| !currentUser.getLoginName().equals(loginName)) {
			throw new UnknownAccountException(); // 如果用户名错误
		}

		String entryPwd = EncryptUtil.encodePassword(password);
		if (!currentUser.getPassword().equals(entryPwd)) {
			throw new IncorrectCredentialsException(); // 如果密码错误
		}

		// 如果身份认证验证成功，返回一个AuthenticationInfo实现；
		SecurityUtils.getSubject().getSession()
				.setAttribute("user", currentUser);
		return new SimpleAuthenticationInfo(loginName, password, getName());
	}
}
