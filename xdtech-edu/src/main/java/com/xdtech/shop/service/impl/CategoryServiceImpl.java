package com.xdtech.shop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.core.dao.BaseDao;
import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.shop.dao.CategoryDao;
import com.xdtech.shop.model.Category;
import com.xdtech.shop.service.CategoryService;
import com.xdtech.shop.vo.CategoryItem;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:01:36
 * @since 1.0
 * @see
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private BaseDao baseDao;

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:01:36
	 * @modified by
	 * @param entity
	 */
	public void save(Category entity) {
		categoryDao.save(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:01:36
	 * @modified by
	 * @param entity
	 */
	public void delete(Category entity) {
		categoryDao.delete(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:01:36
	 * @modified by
	 * @param id
	 */
	public void delete(Long id) {
		categoryDao.delete(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:01:36
	 * @modified by
	 * @param id
	 * @return
	 */
	public Category get(Long id) {
		return categoryDao.get(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:01:36
	 * @modified by
	 * @return
	 */
	public List<Category> getAll() {
		return categoryDao.getAll();
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:01:36
	 * @modified by
	 * @param pg
	 * @param values
	 * @return
	 */
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> categorys = null;
		long rows = 0;
		if (pg!=null) {
			Page<Category> page = categoryDao.findPage(pg,"from Category order by createTime desc", values);
			categorys = BeanUtils.copyListProperties(
					CategoryItem.class, page.getResult());
			rows = page.getTotalItems();
		}else {
			List<Category> categoryList = categoryDao.find("from Category order by id desc", values);
			categorys = BeanUtils.copyListProperties(
					CategoryItem.class, categoryList);
			rows = categorys.size();
		}
		results.put("total", rows);
		results.put("rows", categorys);
		return results;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:01:36
	 * @modified by
	 * @param item
	 * @return
	 */
	public boolean saveOrUpdateCategory(CategoryItem item) {
		Category category = null;
		if (item.getId()==null) {
			category = new Category();
		}else {
			category = categoryDao.get(item.getId());
		}
		//复制前台修改的属性
		BeanUtils.copyProperties(category, item);
		categoryDao.save(category);
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:01:36
	 * @modified by
	 * @param categoryId
	 * @return
	 */
	public CategoryItem loadCategoryItem(Long categoryId) {
		Category category = categoryDao.get(categoryId);
		CategoryItem categoryItem = new CategoryItem();
		BeanUtils.copyProperties(categoryItem, category);
		return categoryItem;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:01:36
	 * @modified by
	 * @param id
	 * @return
	 */
	public boolean deleteCategoryInfo(long id) {
		delete(id);
		return true;
	}
	
	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:01:36
	 * @modified by
	 * @param newIds
	 * @return
	 */
	public boolean deleteCategoryInfo(List<Long> categoryIds) {
		for (Long id : categoryIds) {
			delete(id);
		}
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-15下午9:49:07
	 * @modified by
	 * @param selectCategoryIds
	 * @return
	 */
	public List<Category> loadCategoryByIds(List<Long> selectCategoryIds) {
		return categoryDao.get(selectCategoryIds);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-15下午10:00:02
	 * @modified by
	 * @param valueOf
	 * @return
	 */
	public List<Long> loadCategoryIdsByGoodsId(Long goodsId) {
		Map<String, String> pMap = new HashMap<String, String>();
		pMap.put("goodsId", goodsId.toString());
		List<Object> rs = baseDao.findByNamedQuery("getCategoryIdsByGoodsId", pMap);
		List<Long> gIds = new ArrayList<Long>();
		for (Object ob : rs) {
			gIds.add(Long.valueOf(ob.toString()));
		}
		return gIds;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-17下午11:15:56
	 * @modified by
	 * @return
	 */
	public List<CategoryItem> loadCategoryItems() {
		List<Category> categories = getAll();
		List<CategoryItem> items = new ArrayList<CategoryItem>();
		for (Category cate : categories) {
			CategoryItem categoryItem = new CategoryItem();
			BeanUtils.copyProperties(categoryItem, cate);
			items.add(categoryItem);
		}
		return items;
	}

}
