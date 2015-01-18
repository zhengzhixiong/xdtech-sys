package com.xdtech.shop.service;

import java.util.List;

import com.xdtech.common.service.IBaseService;
import com.xdtech.shop.model.Category;
import com.xdtech.shop.vo.CategoryItem;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:01:36
 * @since 1.0
 * @see
 */
public interface CategoryService extends IBaseService<Category>{

	/**
	 * 保存更新信息
	 * @author max.zheng
	 * @create 2014-12-08 20:01:36
	 * @modified by
	 * @param item
	 * @return
	 */
	boolean saveOrUpdateCategory(CategoryItem item);

	/**
	 * 加载记录信息
	 * @author max.zheng
	 * @create 2014-12-08 20:01:36
	 * @modified by
	 * @param newId
	 * @return
	 */
	CategoryItem loadCategoryItem(Long categoryId);

	/**
	 * 根据id号删除记录信息
	 * @author max.zheng
	 * @create 2014-12-08 20:01:36
	 * @modified by
	 * @param id
	 * @return
	 */
	boolean deleteCategoryInfo(long id);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-08 20:01:36
	 * @modified by
	 * @param categoryIds
	 */
	boolean deleteCategoryInfo(List<Long> categoryIds);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-15下午9:48:58
	 * @modified by
	 * @param selectCategoryIds
	 * @return
	 */
	List<Category> loadCategoryByIds(List<Long> selectCategoryIds);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-15下午9:59:53
	 * @modified by
	 * @param valueOf
	 * @return
	 */
	List<Long> loadCategoryIdsByGoodsId(Long goodsId);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-17下午11:15:43
	 * @modified by
	 * @return
	 */
	List<CategoryItem> loadCategoryItems();
}
