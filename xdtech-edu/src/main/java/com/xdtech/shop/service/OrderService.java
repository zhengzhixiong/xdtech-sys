package com.xdtech.shop.service;

import java.util.List;
import java.util.Map;

import com.xdtech.common.service.impl.IBaseService;
import com.xdtech.core.model.BaseCondition;
import com.xdtech.shop.model.Order;
import com.xdtech.shop.vo.OrderItem;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:33:28
 * @since 1.0
 * @see
 */
public interface OrderService extends IBaseService<Order>{

	/**
	 * 保存更新信息
	 * @author max.zheng
	 * @create 2014-12-08 20:33:28
	 * @modified by
	 * @param item
	 * @return
	 */
	boolean saveOrUpdateOrder(OrderItem item);

	/**
	 * 加载记录信息
	 * @author max.zheng
	 * @create 2014-12-08 20:33:28
	 * @modified by
	 * @param newId
	 * @return
	 */
	OrderItem loadOrderItem(Long orderId);

	/**
	 * 根据id号删除记录信息
	 * @author max.zheng
	 * @create 2014-12-08 20:33:28
	 * @modified by
	 * @param id
	 * @return
	 */
	boolean deleteOrderInfo(long id);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-08 20:33:28
	 * @modified by
	 * @param orderIds
	 */
	boolean deleteOrderInfo(List<Long> orderIds);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-20下午1:33:28
	 * @modified by
	 * @param pg
	 * @param id
	 * @return
	 */
	Map<String, Object> loadOrdersWithMemEmail(Pagination pg, String email);

	/**
	 * 审核通过订单
	 * @author max.zheng
	 * @create 2014-12-20下午9:25:18
	 * @modified by
	 * @param orderId
	 */
	boolean passOrder(Long orderId);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-20下午9:35:35
	 * @modified by
	 * @param orderId
	 * @return
	 */
	boolean voidOrder(List<Long> orderIds);
	
	public Map<String, Object> loadPageCondition(Pagination pg,
			final BaseCondition condition);
}
