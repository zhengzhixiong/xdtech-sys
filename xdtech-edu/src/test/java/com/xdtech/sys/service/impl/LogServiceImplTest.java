package com.xdtech.sys.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.xdtech.core.orm.test.SpringTxTestCase;
import com.xdtech.sys.service.LogService;
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class LogServiceImplTest extends SpringTxTestCase {
	@Autowired
	private LogService logService;

//	@Test
//	public void testLoadPageAndCondition() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSave() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteT() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteLong() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGet() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetAll() {
		System.out.println(logService.getAll().size());
	}

}
