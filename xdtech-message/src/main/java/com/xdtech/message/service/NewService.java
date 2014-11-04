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
package com.xdtech.message.service;

import com.xdtech.common.service.impl.IBaseService;
import com.xdtech.message.model.New;
import com.xdtech.message.vo.NewItem;

/**
 * 
 * @author max.zheng
 * @create 2014-10-27下午9:33:21
 * @since 1.0
 * @see
 */
public interface NewService extends IBaseService<New>{

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-10-27下午11:19:47
	 * @modified by
	 * @param item
	 * @return
	 */
	boolean saveOrUpdateNew(NewItem item);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-10-27下午11:36:19
	 * @modified by
	 * @param newId
	 * @return
	 */
	NewItem loadNewItem(Long newId);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-10-27下午11:43:20
	 * @modified by
	 * @param id
	 * @return
	 */
	boolean deleteNewInfo(long id);

}
