package com.xdtech.sys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.sys.model.MenuFunction;

@Repository
public class PermissionDao extends HibernateDao<MenuFunction, Long> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<MenuFunction> mfRowMapper = new MenuFunRowMapper();

	public List<MenuFunction> getFunctionsByRoleIds(List<Long> roleIds) {
		// select distinct mf from MenuFunction mf left join mf.roles r where
		// r.id in (:roleIds)
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleIds", roleIds);
		Query query = createQuery(
				"select distinct mf from MenuFunction mf left join mf.roles r where r.id in (:roleIds)",
				map);
		return query.list();
	}

	public List<MenuFunction> getAllFunctions() {
		// select menu.* from SYS_MENU_FUNCTION_TBL menu where 1=1 and
		// menu.type=0
		return findByHql("select menu from MenuFunction menu where menu.type=0 ",
				new Object[] {});
	}

	
	public List<MenuFunction> getMenuFunctionsByRoleId(Long roleId) {
		String sql = "select menu.* from SYS_MENU_FUNCTION menu where 1=1 and menu.type=0 and menu.menu_function_id in (select p.menu_id from sys_role_menu p where p.role_id=?)";
		return jdbcTemplate.query(sql,new Object[]{roleId},mfRowMapper);
	}

	private class MenuFunRowMapper implements RowMapper<MenuFunction> {
		public MenuFunction mapRow(ResultSet rs, int rowNum) throws SQLException {
			MenuFunction mf = new MenuFunction();
			mf.setId(rs.getLong("MENU_FUNCTION_ID"));
			mf.setCreateTime(rs.getDate("CREATE_TIME"));
			mf.setDescription(rs.getString("DESCRIPTION"));
			mf.setEnabled(rs.getBoolean("ENABLED"));
			mf.setNameCN(rs.getString("NAME_CN"));
			mf.setNameEN(rs.getString("NAME_EN"));
			mf.setImageUrl(rs.getString("IMAGE_URL"));
			mf.setIconName(rs.getString("ICON_NAME"));
			mf.setPageUrl(rs.getString("PAGE_URL"));
			mf.setIconName(rs.getString("ICON_NAME"));
			mf.setOrderIndex(rs.getInt("ORDER_INDEX"));
			mf.setType(rs.getInt("TYPE"));
			mf.setOperationCode(rs.getString("OPERATION_CODE"));
			return mf;
		}

	}

}
