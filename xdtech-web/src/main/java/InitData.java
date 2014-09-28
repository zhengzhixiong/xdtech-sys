

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xdtech.common.utils.EncryptUtil;
import com.xdtech.sys.model.MenuFunction;
import com.xdtech.sys.model.Role;
import com.xdtech.sys.model.User;
import com.xdtech.sys.model.UserGroup;
import com.xdtech.sys.service.MenuFunctionService;
import com.xdtech.sys.service.RoleService;
import com.xdtech.sys.service.UserGroupService;
import com.xdtech.sys.service.UserService;

public class InitData {

	/**
	 * 初始化数据
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = context.getBean(UserService.class);
		RoleService roleService = context.getBean(RoleService.class);
		UserGroupService userGroupService = context.getBean(UserGroupService.class);
		MenuFunctionService menuFunctionService = context.getBean(MenuFunctionService.class);
		
		User root = new User();
		root.setLoginName("root");
		root.setPassword(EncryptUtil.encodePassword("root"));
		userService.save(root);
		
		User admin = new User();
		admin.setLoginName("admin");
		admin.setPassword(EncryptUtil.encodePassword("admin"));
		userService.save(admin);
		
		User normal = new User();
		normal.setLoginName("normal");
		normal.setPassword(EncryptUtil.encodePassword("normal"));
		userService.save(normal);
		
		//老师
		User teacher = new User();
		teacher.setLoginName("teacher");
		teacher.setPassword(EncryptUtil.encodePassword("teacher"));
		userService.save(teacher);
		
		User test = new User();
		test.setLoginName("test");
		test.setPassword(EncryptUtil.encodePassword("test"));
		userService.save(test);
		
		Role superManager = new Role();
		superManager.setEnable(true);
		superManager.setCode("super_manager");
		superManager.setName("超级管理员");
		roleService.save(superManager);
		root.getRoles().add(superManager);
		userService.save(root);
		
		
		
		Role adminManager = new Role();
		adminManager.setEnable(true);
		adminManager.setCode("admin");
		adminManager.setName("管理员");
		roleService.save(adminManager);
		admin.getRoles().add(superManager);
		userService.save(admin);
		
		Role normalManager = new Role();
		normalManager.setEnable(true);
		normalManager.setCode("normal_manager");
		normalManager.setName("普通管理员");
		roleService.save(normalManager);
		normal.getRoles().add(normalManager);
		userService.save(normal);
		
		Role teacherManager = new Role();
		teacherManager.setEnable(true);
		teacherManager.setCode("teacher");
		teacherManager.setName("教师");
		roleService.save(teacherManager);
		teacher.getRoles().add(teacherManager);
		userService.save(teacher);
		
		Role studentRole = new Role();
		studentRole.setEnable(true);
		studentRole.setCode("student");
		studentRole.setName("学生");
		roleService.save(studentRole);
		
		Role visitorRole = new Role();
		visitorRole.setEnable(true);
		visitorRole.setCode("visitor");
		visitorRole.setName("访客");
		roleService.save(visitorRole);
		
		UserGroup rootGroup = new UserGroup();
		rootGroup.setName("学达平台");
		rootGroup.setParent(null);
		userGroupService.save(rootGroup);
		
		UserGroup group1 = new UserGroup();
		group1.setName("学校");
		group1.setParent(rootGroup);
		group1.getUsers().add(test);
		userGroupService.save(group1);
		
		UserGroup group2 = new UserGroup();
		group2.setName("教育机构");
		group2.setParent(rootGroup);
		group2.getUsers().add(test);
		userGroupService.save(group2);
		
		
		UserGroup group11 = new UserGroup();
		group11.setName("厦门大学");
		group11.setParent(group1);
		group11.getUsers().add(test);
		userGroupService.save(group11);
		
		UserGroup group12 = new UserGroup();
		group12.setName("福州大学");
		group12.setParent(group1);
		userGroupService.save(group12);
		
		UserGroup group13 = new UserGroup();
		group13.setName("闽江学院");
		group13.setParent(group1);
		group13.getRoles().add(normalManager);
		userGroupService.save(group13);
		
		
		MenuFunction m1 = new MenuFunction();
		m1.setIconName("icon-save");
		m1.setNameCN("系统管理");
		m1.setOperationCode("system_manager");
		m1.setPageUrl(null);
		m1.getRoles().add(superManager);
		menuFunctionService.save(m1);
		
		
		
		MenuFunction m11 = new MenuFunction();
		m11.setIconName("icon-save");
		m11.setNameCN("用户管理");
		m11.setOperationCode("user_manager");
		m11.setPageUrl("user.do?user");
		m11.setParent(m1);
		m11.getRoles().add(superManager);
		menuFunctionService.save(m11);
		
		MenuFunction m12 = new MenuFunction();
		m12.setIconName("icon-save");
		m12.setNameCN("角色管理");
		m12.setOperationCode("role_manager");
		m12.setPageUrl("role.do?role");
		m12.setParent(m1);
		m12.getRoles().add(superManager);
		menuFunctionService.save(m12);
		
		MenuFunction m13 = new MenuFunction();
		m13.setIconName("icon-save");
		m13.setNameCN("用户组管理");
		m13.setOperationCode("group_manager");
		m13.setPageUrl("group.do?group");
		m13.setParent(m1);
		m13.getRoles().add(superManager);
		menuFunctionService.save(m13);
		
		MenuFunction m14 = new MenuFunction();
		m14.setIconName("icon-save");
		m14.setNameCN("菜单管理");
		m14.setOperationCode("menu_manager");
		m14.setPageUrl("menuFunction.do?sysFunction");
		m14.setParent(m1);
		m14.getRoles().add(superManager);
		menuFunctionService.save(m14);
		
		MenuFunction m2 = new MenuFunction();
		m2.setIconName("icon-save");
		m2.setNameCN("教务管理");
		m2.setOperationCode("study_manager");
		m2.setPageUrl(null);
		m2.getRoles().add(superManager);
		menuFunctionService.save(m2);
		
		MenuFunction m21 = new MenuFunction();
		m21.setIconName("icon-save");
		m21.setNameCN("学籍管理");
		m21.setOperationCode("score_manager");
		m21.setPageUrl("score.do?score");
		m21.setParent(m2);
		m21.getRoles().add(superManager);
		menuFunctionService.save(m21);
		
		MenuFunction m3 = new MenuFunction();
		m3.setIconName("icon-save");
		m3.setNameCN("基础数据");
		m3.setOperationCode("base_data");
		m3.setPageUrl(null);
		m3.getRoles().add(superManager);
		menuFunctionService.save(m3);
		
	}

}
