package com.xdtech.shop.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.core.dao.BaseDao;
import com.xdtech.core.model.BaseCondition;
import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.shop.dao.OrderDao;
import com.xdtech.shop.model.Order;
import com.xdtech.shop.service.OrderService;
import com.xdtech.shop.vo.OrderItem;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:33:28
 * @since 1.0
 * @see
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private BaseDao<Order> baseDao;

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:33:28
	 * @modified by
	 * @param entity
	 */
	public void save(Order entity) {
		orderDao.save(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:33:28
	 * @modified by
	 * @param entity
	 */
	public void delete(Order entity) {
		orderDao.delete(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:33:28
	 * @modified by
	 * @param id
	 */
	public void delete(Long id) {
		orderDao.delete(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:33:28
	 * @modified by
	 * @param id
	 * @return
	 */
	public Order get(Long id) {
		return orderDao.get(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:33:28
	 * @modified by
	 * @return
	 */
	public List<Order> getAll() {
		return orderDao.getAll();
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:33:28
	 * @modified by
	 * @param pg
	 * @param values
	 * @return
	 */
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> orders = null;
		long rows = 0;
		if (pg!=null) {
			Page<Order> page = orderDao.findPage(pg,"from Order order by createTime desc", values);
			orders = BeanUtils.copyListProperties(
					OrderItem.class, page.getResult());
			rows = page.getTotalItems();
		}else {
			List<Order> orderList = orderDao.find("from Order order by id desc", values);
			orders = BeanUtils.copyListProperties(
					OrderItem.class, orderList);
			rows = orders.size();
		}
		results.put("total", rows);
		results.put("rows", orders);
		return results;
	}
	
	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-21下午2:15:06
	 * @modified by
	 * @param pg
	 * @param condition
	 * @return
	 */
	public Map<String, Object> loadPageCondition(Pagination pg,
			BaseCondition condition) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> orders = null;
		long rows = 0;
		Page<Order> page = baseDao.findPageByNamedQuery(pg,"shop.order.getByCondition", condition);
		orders = BeanUtils.copyListProperties(OrderItem.class, page.getResult());
		rows = page.getTotalItems();
		results.put("total", rows);
		results.put("rows", orders);
		return results;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:33:28
	 * @modified by
	 * @param item
	 * @return
	 */
	public boolean saveOrUpdateOrder(OrderItem item) {
		Order order = null;
		if (item.getId()==null) {
			item.setCreateTime(new Date());
			order = new Order();
		}else {
			order = orderDao.get(item.getId());
		}
		//复制前台修改的属性
		BeanUtils.copyProperties(order, item);
		orderDao.save(order);
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:33:28
	 * @modified by
	 * @param orderId
	 * @return
	 */
	public OrderItem loadOrderItem(Long orderId) {
		Order order = orderDao.get(orderId);
		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(orderItem, order);
		return orderItem;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:33:28
	 * @modified by
	 * @param id
	 * @return
	 */
	public boolean deleteOrderInfo(long id) {
		delete(id);
		return true;
	}
	
	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:33:28
	 * @modified by
	 * @param newIds
	 * @return
	 */
	public boolean deleteOrderInfo(List<Long> orderIds) {
		for (Long id : orderIds) {
			delete(id);
		}
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-20下午1:33:38
	 * @modified by
	 * @param pg
	 * @param id
	 * @return
	 */
	public Map<String, Object> loadOrdersWithMemEmail(Pagination pg, String email) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> orders = null;
		long rows = 0;
		if (pg!=null) {
			Page<Order> page = orderDao.findPage(pg,"from Order o where o.email=? order by createTime desc", email);
			orders = BeanUtils.copyListProperties(
					OrderItem.class, page.getResult());
			rows = page.getTotalItems();
			results.put("page", pg.getPage());
			results.put("next",pg.getPage()*pg.getRows()<rows);
			results.put("previous", pg.getPage()>1);
		}
		results.put("total", rows);
		results.put("rows", orders);
		return results;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-20下午9:25:41
	 * @modified by
	 * @param orderId
	 */
	public boolean passOrder(Long orderId) {
		Order order = orderDao.get(orderId);
		//通过的状态是1
		order.setStatus("1");
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-20下午9:35:43
	 * @modified by
	 * @param orderId
	 * @return
	 */
	public boolean voidOrder(List<Long> orderIds) {
		for (Long orderId : orderIds) {
			Order order = orderDao.get(orderId);
			//无效的状态是-1
			order.setStatus("-1");
		}
		return true;
	}

	

}
