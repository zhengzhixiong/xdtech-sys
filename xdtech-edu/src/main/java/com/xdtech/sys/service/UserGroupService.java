package com.xdtech.sys.service;

import java.util.List;

import com.xdtech.common.service.IBaseService;
import com.xdtech.sys.model.UserGroup;
import com.xdtech.sys.vo.UsergroupItem;
import com.xdtech.web.model.ResultMessage;

public interface UserGroupService extends IBaseService<UserGroup> {
	public void save(UserGroup userGroup);
	
	public List<UserGroup> loadUserGroups();

	public UsergroupItem loadUserGroupItem(UsergroupItem item);
	
	public ResultMessage saveUsergroupItem(UsergroupItem item);
	/**
	 * 根据用户id，查询对应的用户组id集合
	 * @param userId
	 * @return
	 */
	public List<Long> loadGroupIdsByUserId(Long userId);
	
	/**
	 * 根据用户组id集合获取对应的用户组集合信息
	 * @param groupIds
	 * @return
	 */
	public List<UserGroup> loadUserGroupsByIds(List<Long> groupIds);
	/**
	 * 
	 * @description 删除用户组关联信息
	 * @author
	 * @create 2014-8-4下午9:37:51
	 * @modified by
	 * @param id 用户组id
	 * @return
	 */
	public boolean deleteGroupLinkInfo(Long id);
}
