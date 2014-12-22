package com.xdtech.stu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.base.dao.GradeDao;
import com.xdtech.common.service.BaseService;
import com.xdtech.common.utils.EmptyUtil;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.edu.model.Teacher;
import com.xdtech.stu.dao.StudentDao;
import com.xdtech.stu.dao.StudentGradeDao;
import com.xdtech.stu.model.Student;
import com.xdtech.stu.model.StudentGrade;
import com.xdtech.stu.service.StudentService;
import com.xdtech.stu.vo.StudentItem;
import com.xdtech.sys.dao.RoleDao;
import com.xdtech.sys.model.MenuFunction;
import com.xdtech.sys.model.Role;
import com.xdtech.sys.model.User;
import com.xdtech.sys.service.MenuFunctionService;
import com.xdtech.web.model.Pagination;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private StudentGradeDao studentGradeDao;
	@Autowired
	private GradeDao gradeDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuFunctionService menuFunctionService;

	
	
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		Map<String, Object> results = new HashMap<String, Object>();

//		 Page<Student> page = studentDao.loadPageByCond(pg,values);

//		Page page = baseDao.findPageByNamedQuery(pg, "student.getByCondition",
//				values);
//
//		List<Object> items = BeanUtils.copyListProperties(StudentItem.class,
//				page.getResult());
//		results.put("total", page.getTotalItems());
//		results.put("rows", items);

		return results;
	}

	
	public boolean saveOrUpdate(StudentItem item) {
		Student student = new Student();
		BeanUtils.copyProperties(student, item);

		studentDao.save(student);

		List<StudentGrade> studentGrades = studentGradeDao
				.getStudentGradesByStudentId(item.getId());

		if (EmptyUtil.isNotEmpty(item.getGrade())) {

			List<Long> grades = item.getGrade();
			// 设置最新 学生与班级对应关系
			List<StudentGrade> sgs = new ArrayList<StudentGrade>();
			

			List<StudentGrade> needRemove = new ArrayList<StudentGrade>();

			Map<Long, StudentGrade> maps = new HashMap<Long, StudentGrade>();
			if (EmptyUtil.isNotEmpty(studentGrades)) {
				for (StudentGrade sg : studentGrades) {
					maps.put(sg.getGrade().getId(), sg);
					if (!grades.contains(sg.getGrade().getId())) {
						// 不包含当前班级
						needRemove.add(sg);
					}
				}
			}


			for (Long gradeId : grades) {
				if (!maps.containsKey(gradeId)) {
					// 旧的不包含就是新增关系
					StudentGrade studentGrade = new StudentGrade();
					studentGrade.setStudent(student);
					studentGrade.setGrade(gradeDao.get(gradeId));
					sgs.add(studentGrade);

				}

			}

			studentGradeDao.saveAll(sgs);
			
			studentGradeDao.deleteAll(needRemove);

		} else {
			// 学生无选择班级,移除之前绑定关系
			if (EmptyUtil.isNotEmpty(studentGrades)) {
				studentGradeDao.deleteAll(studentGrades);
			}

		}
		return true;
	}

	
	public List<Long> getGradeIds(StudentItem item) {
		return studentDao.getStudentInGrade(item.getId());
	}


	public User getUserByName(String name) {
		Student student = studentDao.findUniqueBy("loginName", name);
		User user = null;
		if (student!=null) {
			user = new User();
			BeanUtils.copyProperties(user, student);
		}
		return user;
	}


	public List<MenuFunction> loadStudentMenu() {
		Role role = roleDao.findUniqueBy("code", "student");
		List<MenuFunction> mfs = new ArrayList<MenuFunction>();
		if (role!=null) {
			mfs = menuFunctionService.loadByRoleId(role.getId());
		}
		return mfs;
	}


	public void save(Student entity) {
		studentDao.save(entity);
	}


	public void delete(Student entity) {
		studentDao.delete(entity);
	}


	public void delete(Long id) {
		studentDao.delete(id);
	}


	public Student get(Long id) {
		return studentDao.get(id);
	}


	public List<Student> getAll() {
		return studentDao.getAll();
	}

}
