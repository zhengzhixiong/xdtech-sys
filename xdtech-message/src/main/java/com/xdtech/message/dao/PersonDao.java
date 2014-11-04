package com.xdtech.message.dao;

import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.message.model.Person;

/**
 * 
 * @author max.zheng
 * @create 2014-11-02 00:15:20
 * @since 1.0
 * @see
 */
@Repository
public class PersonDao extends HibernateDao<Person, Long>{

}
