package com.xdtech.sys.dao;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.PageRequest;
import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.sys.model.User;
@Repository
public class UserDao extends HibernateDao<User, Long>{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User findByLoginName(String loginName) {
        Assert.notNull(loginName);
        return findUniqueBy("loginName", StringUtils.trim(loginName));
    }
	
	public Page<User> loadPage(PageRequest pageRequest) {
		Page<User> page = findPage(pageRequest, "from User");
		return page;
	}
	
	public Page<User> loadPageByGroup(PageRequest pageRequest,Long groupId) {
		Page<User> page = null;
		if (groupId==null||groupId<0) {
			page = findPage(pageRequest, "from User");
		}else {
			page = findPage(pageRequest, "select u from UserGroup ug,User u where u.id in elements (ug.users) and ug.id=?",groupId);
		}
		return page;
	}
	
	public void deleteUserLinkGroup(Long userId) {
		jdbcTemplate.execute(String.format("delete from sys_user_usergroup where user_id=%s",userId.toString()));
	}
	
	public void deleteUserLinkRole(Long userId) {
		jdbcTemplate.execute(String.format("delete from sys_user_role where user_id=%s",userId.toString()));
	}
	
	public void saveUserWithGroup(Long userId,Long usergroupId) {
		jdbcTemplate.update("insert into sys_user_usergroup(user_id,usergroup_id) values(?,?)", userId,usergroupId);
	}
	
//	public List<User> getUsersByCond(Map<String, String> conditions) {
//		List<User> users = findByNamedQuery("user.getByCondition", conditions);
//		return users;
//	}

}
