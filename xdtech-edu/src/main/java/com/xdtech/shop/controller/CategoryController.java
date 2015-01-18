package com.xdtech.shop.controller;

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

import com.xdtech.common.fomat.TreeBuilder;
import com.xdtech.common.utils.ApplicationContextUtil;
import com.xdtech.shop.model.Category;
import com.xdtech.shop.service.CategoryService;
import com.xdtech.shop.vo.CategoryItem;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;
import com.xdtech.web.model.TreeItem;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:01:36
 * @since 1.0
 * @see
 */
@Controller
@Scope("prototype")
@RequestMapping("/category.do")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@RequestMapping(params = "category")
	public ModelAndView skipCategory() {
		return new ModelAndView("shop/category/category_ftl");
	}
	
	@RequestMapping(params="loadList")
	@ResponseBody
	public Map<String, Object> loadList(HttpServletRequest request,Pagination pg) {
		Map<String, Object> results = null;
		if (StringUtils.isEmpty(request.getParameter("page"))) {
			//不分页处理
			results = categoryService.loadPageAndCondition(null, null);
		}else {
			results = categoryService.loadPageAndCondition(pg, null);
		}
		
		return results;
	}
	
	@RequestMapping(params="loadAllList")
	@ResponseBody
	public List<CategoryItem> loadAllList(HttpServletRequest request,Pagination pg) {
		List<CategoryItem> results = categoryService.loadCategoryItems();
		return results;
	}
	
	@RequestMapping(params = "editCategory")
	public ModelAndView editCategory(HttpServletRequest request,Long categoryId) {
		if (categoryId!=null) {
			request.setAttribute("categoryItem", categoryService.loadCategoryItem(categoryId));
		}
		return new ModelAndView("shop/category/editCategory_ftl");
	}
	
	@RequestMapping(params = "saveCategory")
	@ResponseBody
	public ResultMessage saveCategory(CategoryItem item) {
		ResultMessage r = new ResultMessage();
		if (categoryService.saveOrUpdateCategory(item)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "deleteCategoryItems")
	@ResponseBody
	public ResultMessage deleteCategoryItems(String ids) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isNotEmpty(ids)) {
			String[] tempIds = ids.split(",");
			List<Long> categoryIds = new ArrayList<Long>();
			for (String id : tempIds) {
				categoryIds.add(Long.valueOf(id));
			}
			categoryService.deleteCategoryInfo(categoryIds);
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	@RequestMapping(params = "refreshAppCategory")
	@ResponseBody
	public ResultMessage refreshAppCategory(HttpServletRequest request) {
		ResultMessage r = new ResultMessage();
		List<CategoryItem> categoryItems = categoryService.loadCategoryItems();
		ApplicationContextUtil.getApplication().setAttribute("categories", categoryItems);
		return r;
	}
	
	
	@RequestMapping(params = "categoryTree")
	@ResponseBody
	public List<TreeItem> categoryTree(HttpServletRequest request) {
		List<TreeItem> items = new ArrayList<TreeItem>();
		Iterable<Category> categories = categoryService.getAll();
		TreeItem item = null;
		TreeItem pItem = null;
		//如果前台有传userId，就要设置用户当前用户组结点为选中状态
		List<Long> selectCategoryIds = new ArrayList<Long>();
		if (StringUtils.isNotEmpty(request.getParameter("goodsId"))) {
			selectCategoryIds = categoryService.loadCategoryIdsByGoodsId(Long.valueOf(request.getParameter("goodsId")));
		}
		
		for (Category category : categories) {
			item = new TreeItem();
			item.setId(category.getId());
			item.setText(category.getName());
			item.setContent(category.getRemark());
			if (category.getParent()!=null) {
				pItem = new TreeItem(category.getParent().getId());
			}
			item.setParent(pItem);
			if (selectCategoryIds.contains(category.getId())) {
				//已有用户组设置选中状态
				item.setChecked(true);
			}
			items.add(item);
		}
		return TreeBuilder.newTreeBuilder(TreeItem.class, Long.class).buildToTreeList(items);
	}

}
