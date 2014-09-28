package com.xdtech.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.common.service.BaseService;
import com.xdtech.common.utils.EmptyUtil;
import com.xdtech.core.dao.BaseDao;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.sys.dao.UserGroupDao;
import com.xdtech.sys.model.UserGroup;
import com.xdtech.sys.service.UserGroupService;
import com.xdtech.sys.vo.UsergroupItem;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;
@Service
@Transactional
public class UserGroupServiceImpl implements UserGroupService {
	@Autowired
	private UserGroupDao userGroupDao;
	@Autowired
	private BaseDao baseDao;
	public void save(UserGroup userGroup) {
		userGroupDao.save(userGroup);
	}
	
	public List<UserGroup> loadUserGroups() {
		return userGroupDao.getAll();
	}
	
	public UsergroupItem loadUserGroupItem(UsergroupItem item) {
		// 加载一个用户组信息
		UserGroup ug = userGroupDao.get(item.getId());
		BeanUtils.copyProperties(item, ug);
		return item;
	}
	
	public ResultMessage saveUsergroupItem(UsergroupItem item) {
		ResultMessage rm = new ResultMessage();
		// 保存菜单
		UserGroup ug = new UserGroup();
		BeanUtils.copyProperties(ug, item);
		if (null!=item.getpGroupId()) {
			UserGroup pUg = new UserGroup();
			pUg.setId(item.getpGroupId());
			ug.setParent(pUg);
		}else {
			ug.setParent(null);
		}
		if (null==item.getId()) {
			//id 为空的话保存新记录
			ug.setId(null);
			userGroupDao.save(ug);
		}else {
			//更新记录
			UserGroup uug = userGroupDao.get(item.getId());
			BeanUtils.copyProperties(uug, item);
			userGroupDao.save(uug);
		}
		return rm;
	}

	public List<Long> loadGroupIdsByUserId(Long userId) {
		Map<String, String> pMap = new HashMap<String, String>();
		pMap.put("userId", userId.toString());
		List<Object> rs = baseDao.findByNamedQuery("getGroupIdsByUserId", pMap);
		List<Long> gIds = new ArrayList<Long>();
		for (Object ob : rs) {
			gIds.add(Long.valueOf(ob.toString()));
		}
		return gIds;
	}

	public List<UserGroup> loadUserGroupsByIds(List<Long> groupIds) {
		return userGroupDao.get(groupIds);
		
	}

	public boolean deleteGroupLinkInfo(Long id) {
		try {
			UserGroup userGroup = userGroupDao.get(id);
//			if (EmptyUtil.isNotEmpty(userGroup.getChildren())) {
//				for (UserGroup ug : userGroup.getChildren()) {
//					deleteGroupLinkInfo(ug.getId());
//				}
//			}
			userGroupDao.delete(userGroup);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public void delete(UserGroup entity) {
		userGroupDao.delete(entity);
	}

	public void delete(Long id) {
		userGroupDao.delete(id);
	}

	public UserGroup get(Long id) {
		return userGroupDao.get(id);
	}

	public List<UserGroup> getAll() {
		return userGroupDao.getAll();
	}

	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		// TODO Auto-generated method stub
		return null;
	}

}
