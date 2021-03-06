package com.xdtech.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.sys.dao.DictionaryCodeDao;
import com.xdtech.sys.model.DictionaryCode;
import com.xdtech.sys.service.DictionaryCodeService;
import com.xdtech.sys.vo.DictionaryCodeItem;
import com.xdtech.web.model.Pagination;
@Service
@Transactional
public class DictionaryCodeServiceImpl implements DictionaryCodeService{
	@Autowired
	private DictionaryCodeDao dictionaryCodeDao;
	public void save(DictionaryCode entity) {
		dictionaryCodeDao.save(entity);
	}

	public void delete(DictionaryCode entity) {
		dictionaryCodeDao.delete(entity);
	}

	public void delete(Long id) {
		dictionaryCodeDao.delete(id);
	}

	public DictionaryCode get(Long id) {
		return dictionaryCodeDao.get(id);
	}

	public List<DictionaryCode> getAll() {
		return dictionaryCodeDao.getAll();
	}

	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		Map<String, Object> results = new HashMap<String, Object>();
		Page<DictionaryCode> page = dictionaryCodeDao.findPage(pg);
		List<?> dictionarys = BeanUtils.copyListProperties(DictionaryCodeItem.class, page.getResult());
		results.put("total", page.getTotalItems());
		results.put("rows", dictionarys);
		return results;
	}

}
