package com.xdtech.sys.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.xdtech.common.service.IBaseService;
import com.xdtech.core.orm.test.SpringTxTestCase;
import com.xdtech.sys.model.User;
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserServiceTest extends SpringTxTestCase {
	@Autowired
	private UserService userService;
	@Autowired
	private IBaseService<User> baseService;

	@Test
	public void testGetAll() {
		System.out.println(baseService.getAll().size());
	}

}
