package com.xdtech.sys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.sys.model.Role;
@Repository
public class RoleDao extends HibernateDao<Role, Long>{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Role> roleRowMapper = new RoleRowMapper();
	 
	/**
	 * 通过用户ID号获取用户关联角色（包含用户组和用户各自对应的角色）
	 * @param userId
	 * @return
	 */
	public List<Role> getUserRoles(Long userId) {
		String findRoleByUserIdSqlString = "(SELECT srt.* FROM sys_role srt  LEFT JOIN sys_user_role surt ON srt.role_id = surt.role_id "
		  +" LEFT JOIN sys_user sut  ON sut.user_id = surt.user_id "
		  +" WHERE 1 = 1  AND sut.user_id = ?) "
		  +"UNION"
		  +"(SELECT srt2.* FROM sys_role srt2 "
		  +"LEFT JOIN sys_usergroup_role surt  ON srt2.role_id = surt.role_id "
		  +"LEFT JOIN sys_usergroup sugt  ON sugt.usergroup_id = surt.usergroup_id "
		  +"WHERE 1 = 1  AND sugt.usergroup_id IN "
		  +"(SELECT  t1.usergroup_id FROM  sys_usergroup t1, sys_user_usergroup t2, sys_user t3 "
		  +"WHERE t1.usergroup_id = t2.usergroup_id  AND t2.user_id = t3.user_id  AND t3.user_id = ?))";
		return jdbcTemplate.query(findRoleByUserIdSqlString,new Object[]{userId,userId},roleRowMapper);
	}
	/**
	 * 获取非用户关联角色（排除用户组和用户各自对应的角色）
	 * @param userId
	 * @return
	 */
	public List<Role> getNotUserRoles(Long userId) {
		String findNotUserRoleSql = "select t.* from sys_role t where t.role_id not in (select srt.role_id from sys_role srt,sys_user_role surt where srt.role_id=surt.role_id and surt.user_id=?)" +
				" and t.role_id not in (select t1.role_id from sys_usergroup_role t1 where t1.usergroup_id in (" +
				" SELECT t1.usergroup_id FROM sys_usergroup t1, sys_user_usergroup t2, sys_user t3 WHERE t1.usergroup_id = t2.usergroup_id AND t2.user_id = t3.user_id AND t3.user_id = ?))";
		return jdbcTemplate.query(findNotUserRoleSql,new Object[]{userId,userId},roleRowMapper);
	}
	/**
	 * 加载用户user没关联角色，注意只是针对用户而言，
	 * @param userId
	 * @return
	 */
	public List<Role> findNotUserRole(Long userId) {
		String sql = "select t.* from sys_role t where t.role_id not in (select srt.role_id from sys_role srt,sys_user_role surt where srt.role_id=surt.role_id and surt.user_id=?)";
		return jdbcTemplate.query(sql,new Object[]{userId},roleRowMapper);
	}
	/**
	 * 加载用户user关联角色，注意只是针对用户而言，
	 * @param userId
	 * @return
	 */
	public List<Role> findUserRole(Long userId) {
		String sql = "select t.* from sys_role t where t.role_id in (select srt.role_id from sys_role srt,sys_user_role surt where srt.role_id=surt.role_id and surt.user_id=?)";
		return jdbcTemplate.query(sql,new Object[]{userId},roleRowMapper);
	}
	/**
	 * 
	 * 方法作用：查询用户关联用户组对应的角色集合
	 * @param id
	 * @return 
	 * List<Role>
	 * 2013年12月7日-下午11:41:06
	 * @author Zheng Zhi Xiong
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Role> findUsergroupRole(Long userGroupId) {
//		String sql = "select srt2.* from sys_role srt2 left join sys_usergroup_role surt on srt2.id=surt.role_id left join sys_usergroup sugt on sugt.id=surt.usergroup_id where 1=1 and sugt.id in (select t1.id from sys_usergroup t1,sys_user_usergroup t2,sys_user t3 where t1.id=t2.usergroup_id and t2.user_id=t3.id and t3.id=?)";
		String sql = "select t.* from sys_role t where t.role_id in (select t1.role_id from sys_usergroup_role t1 where t1.usergroup_id=?)";
		return jdbcTemplate.query(sql,new Object[]{userGroupId},roleRowMapper);
	}
	/**
	 * 根据用户组id获取非对应用户组角色集合
	 * @param usergroup
	 * @return
	 */
	public List<Role> findNotUserGroupRole(Long userGroupId) {
		String sql = "select t.* from sys_role t where t.role_id not in (select t1.role_id from sys_usergroup_role t1 where t1.usergroup_id=?)";
		return jdbcTemplate.query(sql,new Object[]{userGroupId},roleRowMapper);
	}
	
	
	 private class RoleRowMapper implements RowMapper<Role> {
	        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Role role = new Role();
	            role.setId(rs.getLong("ROLE_ID"));
	            role.setName(rs.getString("NAME"));
	            role.setCode(rs.getString("CODE"));
	            role.setEnable(rs.getBoolean("ENABLE"));
	            role.setDescription(rs.getString("DESCRIPTION"));
	            role.setSuperuser(rs.getBoolean("IS_SUPERUSER"));
	            return role;
	        }
	        
	    }

}
