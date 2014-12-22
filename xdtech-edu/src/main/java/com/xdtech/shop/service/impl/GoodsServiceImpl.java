package com.xdtech.shop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.common.utils.EmptyUtil;
import com.xdtech.core.dao.BaseDao;
import com.xdtech.core.model.BaseCondition;
import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.shop.dao.GoodsDao;
import com.xdtech.shop.model.Category;
import com.xdtech.shop.model.Goods;
import com.xdtech.shop.model.Order;
import com.xdtech.shop.service.CategoryService;
import com.xdtech.shop.service.GoodsService;
import com.xdtech.shop.vo.GoodsItem;
import com.xdtech.shop.vo.OrderItem;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2014-11-30 21:45:39
 * @since 1.0
 * @see
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private BaseDao<Goods> baseDao;
	@Autowired
	private CategoryService categoryService;

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-30 21:45:39
	 * @modified by
	 * @param entity
	 */
	public void save(Goods entity) {
		goodsDao.save(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-30 21:45:39
	 * @modified by
	 * @param entity
	 */
	public void delete(Goods entity) {
		goodsDao.delete(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-30 21:45:39
	 * @modified by
	 * @param id
	 */
	public void delete(Long id) {
		goodsDao.delete(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-30 21:45:39
	 * @modified by
	 * @param id
	 * @return
	 */
	public Goods get(Long id) {
		return goodsDao.get(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-30 21:45:39
	 * @modified by
	 * @return
	 */
	public List<Goods> getAll() {
		return goodsDao.getAll();
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-30 21:45:39
	 * @modified by
	 * @param pg
	 * @param values
	 * @return
	 */
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> goodss = null;
		long rows = 0;
		if (pg!=null) {
			Page<Goods> page = goodsDao.findPage(pg,"from Goods order by createTime desc", values);
			goodss = BeanUtils.copyListProperties(
					GoodsItem.class, page.getResult());
			rows = page.getTotalItems();
		}else {
			List<Goods> goodsList = goodsDao.find("from Goods order by id desc", values);
			goodss = BeanUtils.copyListProperties(
					GoodsItem.class, goodsList);
			rows = goodss.size();
		}
		results.put("total", rows);
		results.put("rows", goodss);
		return results;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-30 21:45:39
	 * @modified by
	 * @param item
	 * @return
	 */
	public boolean saveOrUpdateGoods(GoodsItem item) {
		Goods goods = null;
		List<Long> selectCategoryIds = new ArrayList<Long>();
		if (StringUtils.isNotEmpty(item.getCategoryIds())) {
			String[] categoryIds = item.getCategoryIds().split(",");
			for (String cId : categoryIds) {
				selectCategoryIds.add(Long.valueOf(cId));
			}
		}
		if (item.getId()==null) {
			goods = new Goods();
		}else {
			goods = goodsDao.get(item.getId());
		}
		//复制前台修改的属性
		BeanUtils.copyProperties(goods, item);
		if (EmptyUtil.isNotEmpty(selectCategoryIds)) {
			List<Category> categories = categoryService.loadCategoryByIds(selectCategoryIds);
			goods.setCategories(categories);
		}
		goodsDao.save(goods);
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-30 21:45:39
	 * @modified by
	 * @param goodsId
	 * @return
	 */
	public GoodsItem loadGoodsItem(Long goodsId) {
		Goods goods = goodsDao.get(goodsId);
		GoodsItem goodsItem = new GoodsItem();
		BeanUtils.copyProperties(goodsItem, goods);
		return goodsItem;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-30 21:45:39
	 * @modified by
	 * @param id
	 * @return
	 */
	public boolean deleteGoodsInfo(long id) {
		delete(id);
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-30下午10:38:45
	 * @modified by
	 * @param newIds
	 * @return
	 */
	public boolean deleteNewInfo(List<Long> newIds) {
		for (Long id : newIds) {
			delete(id);
		}
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-19下午8:50:32
	 * @modified by
	 * @param pg
	 * @param ctgId
	 * @return
	 */
	public Map<String, Object> loadGoodsByCtgId(Pagination pg, Long ctgId) {
		Map<String, Object> results = new HashMap<String, Object>();
		Page<Goods> page = goodsDao.loadPageByCategory(pg, ctgId);
		List<Object> goodsItems = BeanUtils.copyListProperties(
				GoodsItem.class, page.getResult());
		long rows = page.getTotalItems();
		results.put("total", rows);
		results.put("rows", goodsItems);
		results.put("page", pg.getPage());
		results.put("next",pg.getPage()*pg.getRows()<rows);
		results.put("previous", pg.getPage()>1);
		return results;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-21下午3:09:23
	 * @modified by
	 * @param goodsIds
	 */
	public void putawayGoods(List<Long> goodsIds) {
		for (Long goodsId : goodsIds) {
			Goods goods = goodsDao.get(goodsId);
			//上架的状态是1
			goods.setStatus("1");
		}
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-21下午3:09:42
	 * @modified by
	 * @param goodsIds
	 */
	public void soldOutGoods(List<Long> goodsIds) {
		for (Long goodsId : goodsIds) {
			Goods goods = goodsDao.get(goodsId);
			//下架的状态是0
			goods.setStatus("0");
		}
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-21下午3:15:27
	 * @modified by
	 * @param pg
	 * @param condition
	 * @return
	 */
	public Map<String, Object> loadPageCondition(Pagination pg,
			BaseCondition condition) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> goodsList = null;
		long rows = 0;
		Page<Goods> page = baseDao.findPageByNamedQuery(pg,"shop.goods.getByCondition", condition);
		goodsList = BeanUtils.copyListProperties(GoodsItem.class, page.getResult());
		rows = page.getTotalItems();
		results.put("total", rows);
		results.put("rows", goodsList);
		return results;
	}

}
