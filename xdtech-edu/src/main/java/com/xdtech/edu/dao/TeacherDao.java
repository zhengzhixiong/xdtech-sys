package com.xdtech.edu.dao;

import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.edu.model.Teacher;
@Repository
public class TeacherDao extends HibernateDao<Teacher, Long>{

}
