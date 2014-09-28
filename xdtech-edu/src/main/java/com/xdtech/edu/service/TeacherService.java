package com.xdtech.edu.service;

import com.xdtech.common.service.impl.IBaseService;
import com.xdtech.edu.model.Teacher;
import com.xdtech.sys.model.User;

public interface TeacherService extends IBaseService<Teacher>{
	/**
	 * 
	 * @description 根据教师用户名加载对应信息，并将起转成用户对象
	 * @author
	 * @create 2014-8-4下午10:48:18
	 * @modified by
	 * @param name
	 * @return
	 */
	public User getUserByName(String name);

}
