package com.xdtech.show.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.show.service.MemberService;
import com.xdtech.show.vo.MemberItem;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;

/**
 * 
 * @author max.zheng
 * @create 2014-12-06 20:45:01
 * @since 1.0
 * @see
 */
@Controller
@Scope("prototype")
@RequestMapping("/member.do")
public class MemberController {
	@Autowired
	private MemberService memberService;
	@RequestMapping(params = "member")
	public ModelAndView skipMember() {
		return new ModelAndView("show/member/member_ftl");
	}
	
	
	
	@RequestMapping(params="loadList")
	@ResponseBody
	public Map<String, Object> loadList(HttpServletRequest request,Pagination pg) {
		Map<String, Object> results = null;
		if (StringUtils.isEmpty(request.getParameter("page"))) {
			//不分页处理
			results = memberService.loadPageAndCondition(null, null);
		}else {
			results = memberService.loadPageAndCondition(pg, null);
		}
		
		return results;
	}
	
	@RequestMapping(params = "editMember")
	public ModelAndView editMember(HttpServletRequest request,Long memberId) {
		if (memberId!=null) {
			request.setAttribute("memberItem", memberService.loadMemberItem(memberId));
		}
		return new ModelAndView("show/member/editMember_ftl");
	}
	
	@RequestMapping(params = "saveMember")
	@ResponseBody
	public ResultMessage saveMember(MemberItem item) {
		ResultMessage r = new ResultMessage();
		if (memberService.saveOrUpdateMember(item)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "deleteMemberItems")
	@ResponseBody
	public ResultMessage deleteMemberItems(String ids) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isNotEmpty(ids)) {
			String[] tempIds = ids.split(",");
			List<Long> memberIds = new ArrayList<Long>();
			for (String id : tempIds) {
				memberIds.add(Long.valueOf(id));
			}
			memberService.deleteMemberInfo(memberIds);
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	//以下方法是会员门户界面里操作
	@RequestMapping(params = "login")
	@ResponseBody
	public ResultMessage login(String userLogin,String userKey,String remeber,HttpServletRequest request) {
		ResultMessage r = new ResultMessage();
		MemberItem memberItem = memberService.loginCheck(userLogin,userKey);
		if (memberItem!=null) {
			request.getSession().setAttribute("member", memberItem);
			r.setSuccess(true);
		}else {
			r.setMsg("账号或密码有误");
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "join")
	@ResponseBody
	public ResultMessage join(String email,String password,String nickName,HttpServletRequest request) {
		ResultMessage r = new ResultMessage();
		MemberItem memberItem = memberService.register(email,password,nickName);
		if (memberItem!=null) {
			request.getSession().setAttribute("member", memberItem);
			r.setSuccess(true);
		}else {
			r.setMsg("注册失败");
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("member");
		return "redirect:show.do?to&url=index";
	}
	@RequestMapping(params = "toEditMember")
	public ModelAndView toEditMember() {
		return new ModelAndView("show/editInfo_ftl");
	}
	@RequestMapping(params = "updateMember")
	@ResponseBody
	public ResultMessage updateMember(MemberItem item,HttpServletRequest request) {
		ResultMessage r = new ResultMessage();
		if (memberService.saveOrUpdateMember(item)) {
			request.getSession().setAttribute("member", item);
			r.setMsg("更新完成");
			r.setSuccess(true);
		} else {
			r.setMsg("更新失败");
			r.setSuccess(false);
		}
		return r;
	}

}
