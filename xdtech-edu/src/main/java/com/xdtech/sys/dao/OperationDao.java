package com.xdtech.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.sys.model.Operation;
@Repository
public class OperationDao extends HibernateDao<Operation, Long>{
	public List<Operation> findByMenuFunctionId(Long menuId) {
		return findByHql("select op from Operation op where op.type=1 and op.menuFunction.id=? ", new Object[]{menuId});
	}
	
	public List<Operation> findByMenuIdAndRoleId(Long roleId,Long menuId) {
//		select rm.* from SYS_ROLE_MENU_TBL rm where rm.menu_id=?1 and rm.role_id=?2 and operation_id is not null
		return findByHql("", roleId,menuId);
	}
}
