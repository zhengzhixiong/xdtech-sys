package com.xdtech.shop.service;

import java.util.List;
import java.util.Map;

import com.xdtech.common.service.IBaseService;
import com.xdtech.core.model.BaseCondition;
import com.xdtech.shop.model.Goods;
import com.xdtech.shop.vo.GoodsItem;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2014-11-30 21:45:39
 * @since 1.0
 * @see
 */
public interface GoodsService extends IBaseService<Goods>{

	/**
	 * 保存更新信息
	 * @author max.zheng
	 * @create 2014-11-30 21:45:39
	 * @modified by
	 * @param item
	 * @return
	 */
	boolean saveOrUpdateGoods(GoodsItem item);

	/**
	 * 加载记录信息
	 * @author max.zheng
	 * @create 2014-11-30 21:45:39
	 * @modified by
	 * @param newId
	 * @return
	 */
	GoodsItem loadGoodsItem(Long goodsId);

	/**
	 * 根据id号删除记录信息
	 * @author max.zheng
	 * @create 2014-11-30 21:45:39
	 * @modified by
	 * @param id
	 * @return
	 */
	boolean deleteGoodsInfo(long id);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-11-30下午10:38:16
	 * @modified by
	 * @param newIds
	 */
	boolean deleteNewInfo(List<Long> newIds);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-19下午8:50:22
	 * @modified by
	 * @param pg
	 * @param ctgId
	 * @return
	 */
	Map<String, Object> loadGoodsByCtgId(Pagination pg, Long ctgId);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-21下午3:08:50
	 * @modified by
	 * @param goodsIds
	 */
	void putawayGoods(List<Long> goodsIds);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-21下午3:09:34
	 * @modified by
	 * @param goodsIds
	 */
	void soldOutGoods(List<Long> goodsIds);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-21下午3:15:16
	 * @modified by
	 * @param pg
	 * @param condition
	 * @return
	 */
	Map<String, Object> loadPageCondition(Pagination pg, BaseCondition condition);

}
