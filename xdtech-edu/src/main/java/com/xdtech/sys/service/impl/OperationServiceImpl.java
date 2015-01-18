package com.xdtech.sys.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.common.service.impl.BaseService;
import com.xdtech.core.dao.BaseDao;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.sys.common.ConStants;
import com.xdtech.sys.dao.OperationDao;
import com.xdtech.sys.dao.RoleDao;
import com.xdtech.sys.model.MenuFunction;
import com.xdtech.sys.model.Operation;
import com.xdtech.sys.model.Role;
import com.xdtech.sys.model.RolePermission;
import com.xdtech.sys.service.OperationService;
import com.xdtech.sys.vo.OperationItem;
import com.xdtech.web.model.Pagination;
@Service
@Transactional
public class OperationServiceImpl implements OperationService {
	@Autowired
	private OperationDao operationDao;
//	@Autowired
//	private RolePermissionDao rolePermissionDao;
//	@Autowired
//	private RoleMenuDao roleMenuDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private BaseDao baseDao;
	public Long getCountByMenuId(Long menuId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OperationItem> loadOperationsByMenuId(Long menuId) {
		//加载按钮集合
		List<OperationItem> operations  = BeanUtils.copyListProperties(OperationItem.class, operationDao.findByMenuFunctionId(menuId));
		
		return operations;
	}
	
	private List<OperationItem> poToVo(List<Operation> operations) {
		List<OperationItem> items = new ArrayList<OperationItem>();
		OperationItem item = null;
		for (Operation operation : operations) {
			item = new OperationItem();
			BeanUtils.copyProperties(item, operation);
			item.setMenuFunId(operation.getMenuFunction().getId());
			items.add(item);
		}
		return items;
	}

	public boolean saveOrUpdateRecord(
			Map<String, List<OperationItem>> effectRow) {
		//更新记录
		//新增记录
		List<Operation> inserted = voToPo(effectRow.get(ConStants.INSERTED),ConStants.INSERTED);
		//更新记录
		List<Operation> updated = voToPo(effectRow.get(ConStants.UPDATED),ConStants.UPDATED); ;
		//删除记录
		List<Operation> deleted = voToPo(effectRow.get(ConStants.DELETED),ConStants.DELETED);;
		
		inserted.addAll(updated);
		operationDao.saveAll(inserted);
		operationDao.deleteAll(deleted);
		return true;
	}
	
	private List<Operation> voToPo(List<OperationItem> operations,String actionType) {
		List<Operation> items = new ArrayList<Operation>();
		Operation item = null;
		for (OperationItem operation : operations) {
			item = new Operation();
			BeanUtils.copyProperties(item, operation);
			if (ConStants.INSERTED.equals(actionType)) {
				MenuFunction mf = new MenuFunction();
				mf.setId(operation.getMenuFunId());
				item.setMenuFunction(mf);
				
			}else if (ConStants.UPDATED.equals(actionType)) {
				//数据库获取最新记录，再更新
				item = operationDao.get(operation.getId());
				BeanUtils.copyProperties(item, operation);
			}else if (ConStants.DELETED.equals(actionType)) {
				
			}
			items.add(item);
		}
		return items;
	}

	public List<OperationItem> loadRoleOperations(Long menuId, Long roleId) {
		if (menuId==null||roleId==null) {
			return null;
		}
		//加载按钮集合
		List<Operation> operations  = operationDao.findByMenuFunctionId(menuId);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("menuId", menuId.toString());
		parameters.put("roleId", roleId.toString());
		List<Object[]> roleOperations = baseDao.findByNamedQuery("getOperationByRoleIdAndMenuId", parameters);
		List<Long> roleOperationIds = new ArrayList<Long>();
		for (Object[] rp : roleOperations) {
			roleOperationIds.add(((BigInteger)rp[0]).longValue());
		}
		List<OperationItem> operationItems = poToVo(operations);
		for (OperationItem operationItem : operationItems) {
			if (roleOperationIds.contains(operationItem.getId())) {
				operationItem.setChecked(true);
			}
		}
		return operationItems;
//		return null;
	}

	public boolean updateRoleOperations(List<OperationItem> items,Long roleId,Long menuId) {
		// 更新按钮权限
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("menuId", menuId.toString());
		parameters.put("roleId", roleId.toString());
		//先全部删除
		baseDao.excuteByNamedQuery("delOperationBtnByRoleIdAndMenuId", parameters);
		
		List<RolePermission> rolePermissions = new ArrayList<RolePermission>();
		RolePermission rolePermission = null;
		
		for (OperationItem item : items) {
			rolePermission = new RolePermission();
			rolePermission.setMenuId(item.getId());
			rolePermission.setRoleId(roleId);
			rolePermissions.add(rolePermission);
		}
		//保存
		baseDao.saveAll(rolePermissions);
		return true;
	}



	
	
	

}
