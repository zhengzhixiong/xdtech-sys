package com.xdtech.stu.service;

import java.util.List;

import com.xdtech.common.service.impl.IBaseService;
import com.xdtech.stu.model.Student;
import com.xdtech.stu.vo.StudentItem;
import com.xdtech.sys.model.MenuFunction;
import com.xdtech.sys.model.User;

public interface StudentService extends IBaseService<Student>{
	public boolean saveOrUpdate(StudentItem item);
	
	public List<Long> getGradeIds(StudentItem item);
	/**
	 * 
	 * @description
	 * @author
	 * @create 2014-8-5下午8:37:43
	 * @modified by
	 * @param name
	 * @return
	 */
	public User getUserByName(String name);
	/**
	 * 
	 * @description 加载学生默认配置的权限菜单
	 * @author
	 * @create 2014-8-5下午8:47:17
	 * @modified by
	 * @return
	 */
	public List<MenuFunction> loadStudentMenu();
}
