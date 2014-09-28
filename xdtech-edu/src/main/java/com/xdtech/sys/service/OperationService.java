package com.xdtech.sys.service;

import java.util.List;
import java.util.Map;

import com.xdtech.common.service.impl.IBaseService;
import com.xdtech.sys.model.Operation;
import com.xdtech.sys.vo.OperationItem;

public interface OperationService {

	public Long getCountByMenuId(Long menuId);
	
	public List<OperationItem> loadOperationsByMenuId(Long menuId);
	
	public List<OperationItem> loadRoleOperations(Long menuId,Long roleId);
	
	public boolean saveOrUpdateRecord(Map<String, List<OperationItem>> effectRow);
	
	public boolean updateRoleOperations(List<OperationItem> items,Long roleId,Long menuId);
	/**
	 * 加载用户的权限编码
	 * @param userId
	 * @return
	 */
//	public Map<String, String> loadUserOperationCodes(Long userId);
}
