package com.xdtech.base.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.xdtech.base.service.GradeService;
import com.xdtech.core.orm.test.SpringTxTestCase;
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class GradeServiceImplTest extends SpringTxTestCase {
	@Autowired
	private GradeService gradeService;

	@Test
	public void testLoadPageAndCondition() {
		
	}

	@Test
	public void testSaveOrUpdateRecord() {
		
	}

	@Test
	public void testGetAll() {
		System.out.println(gradeService.getAll().size());
	}

}
