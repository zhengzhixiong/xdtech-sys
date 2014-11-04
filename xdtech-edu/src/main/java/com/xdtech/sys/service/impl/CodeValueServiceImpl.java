/*
 * Copyright 2013-2014 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xdtech.sys.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.sys.dao.CodeValueDao;
import com.xdtech.sys.model.CodeValue;
import com.xdtech.sys.model.DictionaryCode;
import com.xdtech.sys.service.CodeValueService;
import com.xdtech.sys.service.DictionaryCodeService;
import com.xdtech.sys.vo.CodeValueItem;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2014-9-29下午9:40:42
 * @since 1.0
 * @see
 */
@Service
@Transactional
public class CodeValueServiceImpl implements CodeValueService{
	@Autowired
	private CodeValueDao codeValueDao;
	@Autowired
	private DictionaryCodeService dictionaryCodeService;

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-9-29下午9:40:58
	 * @modified by
	 * @param entity
	 */
	public void save(CodeValue entity) {
		codeValueDao.save(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-9-29下午9:40:58
	 * @modified by
	 * @param entity
	 */
	public void delete(CodeValue entity) {
		codeValueDao.delete(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-9-29下午9:40:58
	 * @modified by
	 * @param id
	 */
	public void delete(Long id) {
		codeValueDao.delete(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-9-29下午9:40:58
	 * @modified by
	 * @param id
	 * @return
	 */
	public CodeValue get(Long id) {
		return codeValueDao.get(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-9-29下午9:40:58
	 * @modified by
	 * @return
	 */
	public List<CodeValue> getAll() {
		return codeValueDao.getAll();
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-9-29下午9:40:58
	 * @modified by
	 * @param pg
	 * @param values
	 * @return
	 */
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		Map<String, Object> results = new HashMap<String, Object>();
		Page<CodeValue> page = codeValueDao.findPage(pg);
		List<?> codeValues = BeanUtils.copyListProperties(CodeValueItem.class, page.getResult());
		results.put("total", page.getTotalItems());
		results.put("rows", codeValues);
		return results;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-9-29下午10:17:34
	 * @modified by
	 * @param pg
	 * @param dictionaryId
	 * @return
	 */
	public Map<String, Object> loadByDictionaryId(Pagination pg,
			Long dictionaryId) {
		Map<String, Object> results = new HashMap<String, Object>();
		Page<CodeValue> page = codeValueDao.findPage(pg,"from CodeValue where dictionaryCode.id=?",dictionaryId);
		List<?> codeValues = BeanUtils.copyListProperties(CodeValueItem.class, page.getResult());
		results.put("total", page.getTotalItems());
		results.put("rows", codeValues);
		return results;
	}
	
	public Map<String, List<CodeValueItem>> loadDictionMapItems() {
		Map<String, List<CodeValueItem>> mapList = new HashMap<String, List<CodeValueItem>>();
		List<DictionaryCode> dictionaryCodes = dictionaryCodeService.getAll();
		for (DictionaryCode dictionaryCode : dictionaryCodes) {
			List<CodeValueItem> items = BeanUtils.copyListProperties(CodeValueItem.class, dictionaryCode.getCodeValues());
			mapList.put(dictionaryCode.getKey(), items);
		}
		return mapList;
	}

}
