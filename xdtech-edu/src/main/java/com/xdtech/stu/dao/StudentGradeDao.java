package com.xdtech.stu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.stu.model.StudentGrade;
@Repository
public class StudentGradeDao extends HibernateDao<StudentGrade, Long>{
	
	public List<StudentGrade> getStudentGradesByStudentId(Long studentId) {
		return findByHql("from StudentGrade sg where sg.student.id = ?", studentId);
	}
}
