package com.xdtech.shop.dao;

import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.shop.model.Goods;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2014-11-30 21:45:39
 * @since 1.0
 * @see
 */
@Repository
public class GoodsDao extends HibernateDao<Goods, Long>{

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-19下午8:54:15
	 * @modified by
	 * @param pg
	 * @param ctgId
	 * @return
	 */
	public Page<Goods> loadPageByCategory(Pagination pg, Long ctgId) {
		Page<Goods> page = null;
		if (ctgId==null||ctgId<0) {
			page = findPage(pg, "from Goods g where g.status='1'");
		}else {
			page = findPage(pg, "select g from Goods g,Category c where g.status='1' and g.id in elements (c.goodsList) and c.id=?",ctgId);
		}
		return page;
	}

}
