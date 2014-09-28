package com.xdtech.edu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.edu.dao.TeacherDao;
import com.xdtech.edu.model.Teacher;
import com.xdtech.edu.service.TeacherService;
import com.xdtech.sys.model.User;
import com.xdtech.web.model.Pagination;
@Service
@Transactional
public class TeacherServiceImpl  implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;
	public User getUserByName(String name) {
		Teacher teacher = teacherDao.findUniqueBy("loginName", name);
		User user = null;
		if (teacher!=null) {
			user = new User();
			BeanUtils.copyProperties(user, teacher);
		}
		return user;
	}
	public void save(Teacher entity) {
		teacherDao.save(entity);
	}
	public void delete(Teacher entity) {
		teacherDao.delete(entity);
	}
	public void delete(Long id) {
		teacherDao.delete(id);
	}
	public Teacher get(Long id) {
		return teacherDao.get(id);
	}
	public List<Teacher> getAll() {
		return teacherDao.getAll();
	}
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		return null;
	}

	
}
