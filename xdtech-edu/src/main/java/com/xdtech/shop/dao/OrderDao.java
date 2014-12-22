package com.xdtech.shop.dao;

import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.shop.model.Order;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:33:28
 * @since 1.0
 * @see
 */
@Repository
public class OrderDao extends HibernateDao<Order, Long>{

}
