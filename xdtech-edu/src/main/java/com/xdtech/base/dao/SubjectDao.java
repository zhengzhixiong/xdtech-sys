package com.xdtech.base.dao;

import org.springframework.stereotype.Repository;

import com.xdtech.base.model.Subject;
import com.xdtech.core.orm.hibernate.HibernateDao;
@Repository
public class SubjectDao extends HibernateDao<Subject, Long>{

}
