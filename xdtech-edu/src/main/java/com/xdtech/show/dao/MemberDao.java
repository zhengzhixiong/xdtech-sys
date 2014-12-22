package com.xdtech.show.dao;

import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.show.model.Member;

/**
 * 
 * @author max.zheng
 * @create 2014-12-06 20:45:01
 * @since 1.0
 * @see
 */
@Repository
public class MemberDao extends HibernateDao<Member, Long>{

}
