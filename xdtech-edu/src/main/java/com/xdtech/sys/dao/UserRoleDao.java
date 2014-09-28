package com.xdtech.sys.dao;

import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.sys.model.UserRole;

@Repository
public class UserRoleDao  extends HibernateDao<UserRole, Long>{

}
