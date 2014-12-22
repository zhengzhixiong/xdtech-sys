package com.xdtech.show.service;

import java.util.List;

import com.xdtech.common.service.impl.IBaseService;
import com.xdtech.show.model.Member;
import com.xdtech.show.vo.MemberItem;

/**
 * 
 * @author max.zheng
 * @create 2014-12-06 20:45:01
 * @since 1.0
 * @see
 */
public interface MemberService extends IBaseService<Member>{

	/**
	 * 保存更新信息
	 * @author max.zheng
	 * @create 2014-12-06 20:45:01
	 * @modified by
	 * @param item
	 * @return
	 */
	boolean saveOrUpdateMember(MemberItem item);

	/**
	 * 加载记录信息
	 * @author max.zheng
	 * @create 2014-12-06 20:45:01
	 * @modified by
	 * @param newId
	 * @return
	 */
	MemberItem loadMemberItem(Long memberId);

	/**
	 * 根据id号删除记录信息
	 * @author max.zheng
	 * @create 2014-12-06 20:45:01
	 * @modified by
	 * @param id
	 * @return
	 */
	boolean deleteMemberInfo(long id);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-06 20:45:01
	 * @modified by
	 * @param memberIds
	 */
	boolean deleteMemberInfo(List<Long> memberIds);

	/**
	 * 登录认证
	 * @author max.zheng
	 * @create 2014-12-6下午9:45:44
	 * @modified by
	 * @param userLogin
	 * @param userKey
	 * @return
	 */
	MemberItem loginCheck(String userLogin, String userKey);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-7下午8:34:22
	 * @modified by
	 * @param email
	 * @param password
	 * @param nickName
	 * @return
	 */
	MemberItem register(String email, String password, String nickName);
}
