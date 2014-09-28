package com.xdtech.sys.dao;

import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.sys.model.Log;
@Repository
public class LogDao extends HibernateDao<Log, Long>{

}
