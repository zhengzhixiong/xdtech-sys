package com.xdtech.message.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.message.dao.NewDao;
import com.xdtech.message.model.New;
import com.xdtech.message.service.NewService;
import com.xdtech.message.vo.NewItem;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2014-10-27下午9:34:30
 * @since 1.0
 * @see
 */
@Service
@Transactional
public class NewServiceImpl implements NewService {
	@Autowired
	private NewDao newDao;

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-10-27下午9:34:30
	 * @modified by
	 * @param entity
	 */
	public void save(New entity) {
		newDao.save(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-10-27下午9:34:30
	 * @modified by
	 * @param entity
	 */
	public void delete(New entity) {
		newDao.delete(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-10-27下午9:34:30
	 * @modified by
	 * @param id
	 */
	public void delete(Long id) {
		newDao.delete(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-10-27下午9:34:30
	 * @modified by
	 * @param id
	 * @return
	 */
	public New get(Long id) {
		return newDao.get(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-10-27下午9:34:30
	 * @modified by
	 * @return
	 */
	public List<New> getAll() {
		return newDao.getAll();
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-10-27下午9:34:30
	 * @modified by
	 * @param pg
	 * @param values
	 * @return
	 */
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> news = null;
		long rows = 0;
		if (pg!=null) {
			Page<New> page = newDao.findPage(pg,"from New order by createTime desc", values);
			news = BeanUtils.copyListProperties(
					NewItem.class, page.getResult());
			rows = page.getTotalItems();
		}else {
			List<New> newList = newDao.find("from New order by id desc", values);
			news = BeanUtils.copyListProperties(
					NewItem.class, newList);
			rows = news.size();
		}
		results.put("total", rows);
		results.put("rows", news);
		return results;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-10-27下午11:20:11
	 * @modified by
	 * @param item
	 * @return
	 */
	public boolean saveOrUpdateNew(NewItem item) {
		New new1 = null;
		if (item.getId()==null) {
			new1 = new New();
		}else {
			new1 = newDao.get(item.getId());
		}
		//复制前台修改的属性
		BeanUtils.copyProperties(new1, item);
		newDao.save(new1);
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-10-27下午11:36:41
	 * @modified by
	 * @param newId
	 * @return
	 */
	public NewItem loadNewItem(Long newId) {
		New new1 = newDao.get(newId);
		NewItem newItem = new NewItem();
		BeanUtils.copyProperties(newItem, new1);
		return newItem;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-10-27下午11:43:30
	 * @modified by
	 * @param id
	 * @return
	 */
	public boolean deleteNewInfo(long id) {
		delete(id);
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-4下午11:06:25
	 * @modified by
	 * @param ids
	 * @return
	 */
	public boolean deleteNewInfo(List<Long> ids) {
		for (Long id : ids) {
			delete(id);
		}
		return true;
	}

}
