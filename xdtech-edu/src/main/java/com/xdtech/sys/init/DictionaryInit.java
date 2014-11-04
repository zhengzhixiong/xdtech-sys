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

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thoughtworks.xstream.XStream;
import com.xdtech.common.service.BaseService;
import com.xdtech.common.utils.ApplicationContextUtil;
import com.xdtech.common.utils.EmptyUtil;
import com.xdtech.core.init.InitCacheData;
import com.xdtech.core.init.SysInitOperation;
import com.xdtech.core.model.BaseModel;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.sys.model.CodeValue;
import com.xdtech.sys.model.DictionaryCode;
import com.xdtech.sys.service.CodeValueService;
import com.xdtech.sys.vo.CodeValueItem;

/**
 * 数据字典初始化实现类
 * @author max.zheng
 * @create 2014-9-25下午9:42:01
 * @since 1.0
 * @see
 */
public class DictionaryInit implements SysInitOperation{
	Log log = LogFactory.getLog(DictionaryInit.class);

	/**
	 * 数据字典初始化
	 * @author max.zheng
	 * @create 2014-9-25下午9:42:14
	 * @modified by
	 */
	public void initingToDb(BaseService<BaseModel> baseService) {
		log.info("数据字典初始化开始......");
		XStream xstream = new XStream(); 
	    Reader reader = new InputStreamReader(  
	    		DictionaryInit.class.getResourceAsStream("/com/xdtech/sys/init/DictionaryCode.xml"));  
//		String filePath = DictionaryInit.class.getResource("").getPath()+"DictionaryCode.xml";
//		InputStream is = this.getClass().getResourceAsStream("/DictionaryCode.xml");
		
        List<DictionaryCode> dictionaryCodes = (List<DictionaryCode>) xstream.fromXML(reader);
        for (DictionaryCode dictionaryCode : dictionaryCodes) {
        	baseService.save(dictionaryCode);
        	List<CodeValue> codeValues = dictionaryCode.getCodeValues();
        	if (EmptyUtil.isNotEmpty(codeValues)) {
				for (CodeValue codeValue : codeValues) {
					codeValue.setDictionaryCode(dictionaryCode);
					baseService.save(codeValue);
				}
			}
		}
        
        log.info("数据字典初始化结束......");
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-10-12下午9:18:03
	 * @modified by
	 * @param baseService
	 */
	public void initingToCache(BaseService<BaseModel> baseService) {
		CodeValueService codeValueService = ApplicationContextUtil.getContext().getBean(CodeValueService.class);
		Map<String, List<CodeValueItem>> codeMap = codeValueService.loadDictionMapItems();
		for(String key:codeMap.keySet()) {
			InitCacheData.dictionary.put(key,codeMap.get(key));
		}
	}

}
