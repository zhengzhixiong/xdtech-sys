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
package com.xdtech.shop.init;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xdtech.common.service.BaseService;
import com.xdtech.core.init.SysInitOperation;
import com.xdtech.core.model.BaseModel;
import com.xdtech.sys.init.DictionaryInit;

/**
 * 
 * @author max.zheng
 * @create 2014-12-15下午10:24:39
 * @since 1.0
 * @see
 */
public class CategoryInit implements SysInitOperation{
	Log log = LogFactory.getLog(CategoryInit.class);

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-15下午10:25:14
	 * @modified by
	 * @param baseService
	 */
	public void initingToDb(BaseService<BaseModel> baseService) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-15下午10:25:14
	 * @modified by
	 * @param baseService
	 */
	public void initingToCache(BaseService<BaseModel> baseService) {
		
	}
}
