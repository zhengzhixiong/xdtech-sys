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
package com.xdtech.sys.service;

import java.util.List;
import java.util.Map;

import com.xdtech.common.service.IBaseService;
import com.xdtech.sys.model.CodeValue;
import com.xdtech.sys.vo.CodeValueItem;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2014-9-29下午9:39:34
 * @since 1.0
 * @see
 */
public interface CodeValueService extends IBaseService<CodeValue> {

	Map<String, Object> loadByDictionaryId(Pagination pg,Long dictionaryId);
	
	public Map<String, List<CodeValueItem>> loadDictionMapItems();
	
}
