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
import com.xdtech.stu.service.StudentService;
import com.xdtech.sys.model.MenuFunction;
import com.xdtech.sys.model.User;
import com.xdtech.sys.vo.RoleItem;
//@Component
public class AuthRealm extends AuthorizingRealm {
//	@Autowired
	private UserService userService; //= ApplicationContextUtil.getContext().getBean(UserServiceImpl.class);
//	private TeacherService teacherService = ApplicationContextUtil.getContext().getBean(TeacherServiceImpl.class);
//	@Autowired
	private StudentService studentService;// = ApplicationContextUtil.getContext().getBean(StudentServiceImpl.class);
//	private MenuFunctionService menuFunctionService = ApplicationContextUtil.getContext().getBean(MenuFunctionServiceImpl.class);
	
//	private final static ThreadLocal<String> roleFlagLocal = new ThreadLocal<String>();
//	//角色标记
	private String roleFlag;
	
	//管理员
	private static String MANAGER_ROLE = "manager";
	//教师
	private static String TEACHER_ROLE = "teacher";
	//学生
	private static String STUDENT_ROLE = "student";
	//访客
	private static String VISITOR_ROLE = "visitor";
	


	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
		// 如果是超级管理员，则拥有所有权限 modify by 
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		
		List<Long> roleIds = new ArrayList<Long>();
		List<MenuFunction> menuFunctions = null;
//		String roleFlag = roleFlagLocal.get().toString();
		if (MANAGER_ROLE.equals(roleFlag)||TEACHER_ROLE.equals(roleFlag)||VISITOR_ROLE.equals(roleFlag)) {
			//管理员身份 或者 教师身份 或者 访客身份
			Long userId = currentUser.getId();
			List<RoleItem> roles = userService.loadUserRoles(userId);
			for (RoleItem role : roles) {
				authInfo.addRole(role.getCode());
				roleIds.add(role.getId());
				//如果是超级管理员的话
				if (role.isSuperuser()) {
					//拥有所有权限
					authInfo.addStringPermission("*");
					return authInfo;
				}
			}
			menuFunctions = userService.loadUserFunctions(userId, roleIds);
			
		}  else if (STUDENT_ROLE.equals(roleFlag)) {
			//学生身份
			menuFunctions = studentService.loadStudentMenu();
		} 
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
		
		User currentUser = null;
//		String roleFlag = roleFlagLocal.get().toString();
		if (MANAGER_ROLE.equals(roleFlag)||TEACHER_ROLE.equals(roleFlag)||VISITOR_ROLE.equals(roleFlag)) {
//			UserServiImpl =ce t (UserService) ApplicationContextUtil.getContext().getBean("userServiceImpl");
//			currentUser = tImpl.getUserByName(loginName);
			//管理员身份 或者 教师身份 或者 访客身份
			currentUser = userService.getUserByName(loginName);
		}  else if (STUDENT_ROLE.equals(roleFlag)) {
			//学生身份
			currentUser = studentService.getUserByName(loginName);
		} 
		
		if (currentUser==null||!currentUser.getLoginName().equals(loginName)) {
			throw new UnknownAccountException();       // 如果用户名错误
		}
		String entryPwd = EncryptUtil.encodePassword(password);
		if (!currentUser.getPassword().equals(entryPwd)) {
			throw new IncorrectCredentialsException(); // 如果密码错误
		}

		// 如果身份认证验证成功，返回一个AuthenticationInfo实现；
		SecurityUtils.getSubject().getSession().setAttribute("user", currentUser);
		return new SimpleAuthenticationInfo(loginName, password, getName());
	}

	public void setRoleFlag(String roleFlag) {
		this.roleFlag = roleFlag;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

//	public static ThreadLocal getRoleflaglocal() {
//		return roleFlagLocal;
//	}	
	
	

}
