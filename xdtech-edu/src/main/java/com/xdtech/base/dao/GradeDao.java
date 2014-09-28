package com.xdtech.base.dao;

import org.springframework.stereotype.Repository;

import com.xdtech.base.model.Grade;
import com.xdtech.core.orm.hibernate.HibernateDao;
@Repository
public class GradeDao extends HibernateDao<Grade, Long>{

}
