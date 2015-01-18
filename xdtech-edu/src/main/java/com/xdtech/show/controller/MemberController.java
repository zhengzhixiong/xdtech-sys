package com.xdtech.show.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.xdtech.web.util.AppReturnUtils;

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
			r.setMsg("登录成功");
			r.setObj(memberItem);
			request.getSession().setAttribute("member", memberItem);
		}else {
			r.setSuccess(false);
			r.setMsg("账号或密码有误");
		}
		return r;
	}
	
	@RequestMapping(params = "join")
	@ResponseBody
	public ResultMessage join(String email,String password,String nickName,HttpServletRequest request) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isEmpty(nickName)) {
			nickName = "Member";
		}
		MemberItem memberItem = memberService.register(email,password,nickName);
		if (memberItem!=null) {
			r.setMsg("注册成功");
			r.setObj(memberItem);
			request.getSession().setAttribute("member", memberItem);
		}else {
			r.setMsg("注册失败");
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "checkIsExist")
	@ResponseBody
	public ResultMessage checkIsExist(String email,String nickName,HttpServletRequest request) {
		ResultMessage r = new ResultMessage();
		boolean isExist= memberService.checkMemberEmail(email);
		if (isExist) {
			r.setSuccess(false);
			r.setMsg("邮箱已存在！");
		}else {
			r.setSuccess(true);
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
			r.setSuccess(true);
			r.setMsg("更新完成");
		} else {
			r.setSuccess(false);
			r.setMsg("更新失败");
		}
		return r;
	}
	
	/*******************以下是手机app接口调用方法*********************/
	@RequestMapping(params="appLogin")
	public void appLogin(HttpServletRequest request,HttpServletResponse response,String userLogin,String userKey) {
		ResultMessage rm = login(userLogin, userKey, "", request);
        AppReturnUtils.returnJsonpAsk(request, response, rm);
	}
	
	@RequestMapping(params = "appLogout")
	public void appLogout(HttpServletRequest request,HttpServletResponse response) {
		request.getSession().removeAttribute("member");
		AppReturnUtils.returnJsonpAsk(request, response, new ResultMessage(true, "退出成功"));
	}
	
	@RequestMapping(params = "appRegister")
	public void appRegister(HttpServletRequest request,HttpServletResponse response,String email,String password,String nickName) {
		ResultMessage rm = checkIsExist(email, nickName, request);
		if (rm.isSuccess()) {
			rm = join(email, password, nickName, request);
		}	
		AppReturnUtils.returnJsonpAsk(request, response, rm);
	}

}
