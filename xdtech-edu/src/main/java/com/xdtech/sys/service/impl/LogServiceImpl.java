package com.xdtech.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.sys.dao.LogDao;
import com.xdtech.sys.model.Log;
import com.xdtech.sys.service.LogService;
import com.xdtech.sys.vo.LogItem;
import com.xdtech.web.model.Pagination;
@Service
@Transactional
public class LogServiceImpl implements LogService{
	@Autowired
	private LogDao logDao;

	public Map<String, Object> loadPageAndCondition(Pagination pg,final Map<String,String> values) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> logs = null;
		long rows = 0;
		if (pg!=null) {
			Page<Log> page = logDao.findPage(pg,"from Log order by createTime desc", values);
			logs = BeanUtils.copyListProperties(
					LogItem.class, page.getResult());
			rows = page.getTotalItems();
		}else {
			List<Log> logList = logDao.find("from Log order by id desc", values);
			logs = BeanUtils.copyListProperties(
					LogItem.class, logList);
			rows = logs.size();
		}
		results.put("total", rows);
		results.put("rows", logs);
		
		return results;
	}

	public void save(Log entity) {
		logDao.save(entity);
	}

	public void delete(Log entity) {
		logDao.delete(entity);
	}

	public void delete(Long id) {
		logDao.delete(id);
	}

	public Log get(Long id) {
		return logDao.get(id);
	}

	public List<Log> getAll() {
		return logDao.getAll();
	}

	
	

}
