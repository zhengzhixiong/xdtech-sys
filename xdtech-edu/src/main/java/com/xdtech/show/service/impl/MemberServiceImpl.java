package com.xdtech.show.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.common.utils.encrypt.DESCoder;
import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.show.dao.MemberDao;
import com.xdtech.show.model.Member;
import com.xdtech.show.service.MemberService;
import com.xdtech.show.vo.MemberItem;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2014-12-06 20:45:01
 * @since 1.0
 * @see
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:45:01
	 * @modified by
	 * @param entity
	 */
	public void save(Member entity) {
		memberDao.save(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:45:01
	 * @modified by
	 * @param entity
	 */
	public void delete(Member entity) {
		memberDao.delete(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:45:01
	 * @modified by
	 * @param id
	 */
	public void delete(Long id) {
		memberDao.delete(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:45:01
	 * @modified by
	 * @param id
	 * @return
	 */
	public Member get(Long id) {
		return memberDao.get(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:45:01
	 * @modified by
	 * @return
	 */
	public List<Member> getAll() {
		return memberDao.getAll();
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:45:01
	 * @modified by
	 * @param pg
	 * @param values
	 * @return
	 */
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> members = null;
		long rows = 0;
		if (pg!=null) {
			Page<Member> page = memberDao.findPage(pg,"from Member order by createTime desc", values);
			members = BeanUtils.copyListProperties(
					MemberItem.class, page.getResult());
			rows = page.getTotalItems();
		}else {
			List<Member> memberList = memberDao.find("from Member order by id desc", values);
			members = BeanUtils.copyListProperties(
					MemberItem.class, memberList);
			rows = members.size();
		}
		results.put("total", rows);
		results.put("rows", members);
		return results;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:45:01
	 * @modified by
	 * @param item
	 * @return
	 */
	public boolean saveOrUpdateMember(MemberItem item) {
		Member member = null;
		if (item.getId()==null) {
			item.setPassword(DESCoder.encrypt("123456"));
			member = new Member();
		}else {
			member = memberDao.get(item.getId());
		}
		//复制前台修改的属性
		BeanUtils.copyProperties(member, item);
		memberDao.save(member);
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:45:01
	 * @modified by
	 * @param memberId
	 * @return
	 */
	public MemberItem loadMemberItem(Long memberId) {
		Member member = memberDao.get(memberId);
		MemberItem memberItem = new MemberItem();
		BeanUtils.copyProperties(memberItem, member);
		return memberItem;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:45:01
	 * @modified by
	 * @param id
	 * @return
	 */
	public boolean deleteMemberInfo(long id) {
		delete(id);
		return true;
	}
	
	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:45:01
	 * @modified by
	 * @param newIds
	 * @return
	 */
	public boolean deleteMemberInfo(List<Long> memberIds) {
		for (Long id : memberIds) {
			delete(id);
		}
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-6下午9:46:02
	 * @modified by
	 * @param userLogin
	 * @param userKey
	 * @return
	 */
	public MemberItem loginCheck(String userLogin, String userKey) {
		Member member = memberDao.findUnique("from Member where password=? and (nickName=? or email=?)", DESCoder.encrypt(userKey),userLogin,userLogin);
		if (member!=null) {
			MemberItem item = new MemberItem();
			BeanUtils.copyProperties(item, member);
			return item;
		}else {
			return null;
		}
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-7下午8:34:34
	 * @modified by
	 * @param email
	 * @param password
	 * @param nickName
	 * @return
	 */
	public MemberItem register(String email, String password, String nickName) {
		Member member = new Member();
		member.setEmail(email);
		member.setNickName(nickName);
		//密码加密
		member.setPassword(DESCoder.encrypt(password));
		member.setSex("M");
		save(member);
		MemberItem item = new MemberItem();
		BeanUtils.copyProperties(item, member);
		return item;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-22下午11:10:24
	 * @modified by
	 * @param email
	 * @return
	 */
	public boolean checkMemberEmail(String email) {
		Member member = memberDao.findUnique("from Member where email=?", email);
		return member==null?false:true;

	}

}
