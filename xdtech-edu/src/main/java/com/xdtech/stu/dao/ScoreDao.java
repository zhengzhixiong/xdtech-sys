package com.xdtech.stu.dao;

import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.stu.model.Score;
@Repository
public class ScoreDao extends HibernateDao<Score, Long>{
	
	public Score getBySubGraStu(Long subjectId,Long gradeId,Long studentId) {
		return findUnique("from Score s where s.grade.id=? and s.subject.id=? and s.student.id=?", gradeId,subjectId,studentId);
	}

}
