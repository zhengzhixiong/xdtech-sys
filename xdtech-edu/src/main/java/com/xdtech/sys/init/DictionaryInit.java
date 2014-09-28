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
package com.xdtech.sys.init;

import com.xdtech.common.service.BaseService;
import com.xdtech.core.init.SysInitOperation;
import com.xdtech.core.model.BaseModel;
import com.xdtech.sys.model.DictionaryCode;

/**
 * 数据字典初始化实现类
 * @author max.zheng
 * @create 2014-9-25下午9:42:01
 * @since 1.0
 * @see
 */
public class DictionaryInit implements SysInitOperation{

	/**
	 * 数据字典初始化
	 * @author max.zheng
	 * @create 2014-9-25下午9:42:14
	 * @modified by
	 */
	public void initing(BaseService<BaseModel> baseService) {
		DictionaryCode code = new DictionaryCode();
		code.setKey("key");
		code.setName("test");
		baseService.save(code);
		System.out.println("数据字典初始化");
	}

}
