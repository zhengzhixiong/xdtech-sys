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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.show.model.Article;
import com.xdtech.show.model.Member;
import com.xdtech.show.service.ArticleService;
import com.xdtech.show.vo.ArticleItem;
import com.xdtech.show.vo.MemberItem;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;

/**
 * 
 * @author max.zheng
 * @create 2014-12-06 20:53:58
 * @since 1.0
 * @see
 */
@Controller
@Scope("prototype")
@RequestMapping("/article.do")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@RequestMapping(params = "article")
	public ModelAndView skipArticle() {
		return new ModelAndView("show/article/article_ftl");
	}
	
	@RequestMapping(params="loadList")
	@ResponseBody
	public Map<String, Object> loadList(HttpServletRequest request,Pagination pg) {
		Map<String, Object> results = null;
		if (StringUtils.isEmpty(request.getParameter("page"))) {
			//不分页处理
			results = articleService.loadPageAndCondition(null, null);
		}else {
			results = articleService.loadPageAndCondition(pg, null);
		}
		
		return results;
	}
	
	@RequestMapping(params = "editArticle")
	public ModelAndView editArticle(HttpServletRequest request,Long articleId) {
		if (articleId!=null) {
			request.setAttribute("articleItem", articleService.loadArticleItem(articleId));
		}
		return new ModelAndView("show/article/editArticle_ftl");
	}
	
	@RequestMapping(params = "saveArticle")
	@ResponseBody
	public ResultMessage saveArticle(ArticleItem item) {
		ResultMessage r = new ResultMessage();
		if (articleService.saveOrUpdateArticle(item)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "deleteArticleItems")
	@ResponseBody
	public ResultMessage deleteArticleItems(String ids) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isNotEmpty(ids)) {
			String[] tempIds = ids.split(",");
			List<Long> articleIds = new ArrayList<Long>();
			for (String id : tempIds) {
				articleIds.add(Long.valueOf(id));
			}
			articleService.deleteArticleInfo(articleIds);
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	//以下是show模块方法
	@RequestMapping(params = "postArticle")
	@ResponseBody
	public ResultMessage postArticle(ArticleItem item,HttpServletRequest request) {
		ResultMessage r = new ResultMessage();
		MemberItem memberItem = (MemberItem) request.getSession().getAttribute("member");
		if (memberItem!=null) {
			Article article = new Article();
			article.setTitle(item.getTitle());
			article.setContent(item.getContent());
			article.setMember(new Member(memberItem.getId()));
			articleService.save(article);
		}else {
			r.setMsg("未登录，无法提交");
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "myArticles")
	public ModelAndView myArticles(HttpServletRequest request) {
		MemberItem memberItem = (MemberItem) request.getSession().getAttribute("member");
		List<ArticleItem> articles = articleService.loadArticleItemsByMemberId(memberItem.getId());
		request.setAttribute("articles", articles);
		return new ModelAndView("show/myArticles_ftl");
	}
	
	

}
