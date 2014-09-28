package com.xdtech.sys.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.sys.model.UserGroup;
@Repository
public class UserGroupDao extends HibernateDao<UserGroup, Long>{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void deleteUsergroupLinkRole(Long userGroupId) {
		jdbcTemplate.execute(String.format("delete from sys_usergroup_role where usergroup_id=%s", userGroupId.toString()));
	}
}
