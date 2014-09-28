package com.xdtech.stu.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.PageRequest;
import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.stu.model.Student;
@Repository
public class StudentDao extends HibernateDao<Student, Long>{

	public Page<Student> loadPageByCond(PageRequest pageRequest,Map<String, String> params) {
		return findPage(pageRequest, "from Student s where s.name=:name", params);
	}
	
	public List<Student> getStudentsWithGrade(Long gradeId) {
		return findByHql("from Student s where s in (select sg.student from StudentGrade sg where sg.grade.id=?)", gradeId);
	}
	
	public List<Long> getStudentInGrade(Long studentId) {
		return findByHql("select sg.grade.id from StudentGrade sg where sg.student.id = ?", studentId);
	}

}
