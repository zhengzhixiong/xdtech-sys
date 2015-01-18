package com.xdtech.report.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.common.utils.DateUtil;
import com.xdtech.common.utils.DateUtil.DateStyle;
import com.xdtech.core.dao.BaseDao;
import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.report.dao.ConfigDao;
import com.xdtech.report.model.Config;
import com.xdtech.report.service.ConfigService;
import com.xdtech.report.vo.ConfigItem;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2015-01-14 21:25:22
 * @since 1.0
 * @see
 */
@Service
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	private ConfigDao configDao;
	@Autowired
	private BaseDao baseDao;

	/**
	 * @description
	 * @author max.zheng
	 * @create 2015-01-14 21:25:22
	 * @modified by
	 * @param entity
	 */
	public void save(Config entity) {
		configDao.save(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2015-01-14 21:25:22
	 * @modified by
	 * @param entity
	 */
	public void delete(Config entity) {
		configDao.delete(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2015-01-14 21:25:22
	 * @modified by
	 * @param id
	 */
	public void delete(Long id) {
		configDao.delete(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2015-01-14 21:25:22
	 * @modified by
	 * @param id
	 * @return
	 */
	public Config get(Long id) {
		return configDao.get(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2015-01-14 21:25:22
	 * @modified by
	 * @return
	 */
	public List<Config> getAll() {
		return configDao.getAll();
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2015-01-14 21:25:22
	 * @modified by
	 * @param pg
	 * @param values
	 * @return
	 */
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> configs = null;
		long rows = 0;
		if (pg!=null) {
			Page<Config> page = configDao.findPage(pg,"from Config order by createTime desc", values);
			configs = BeanUtils.copyListProperties(
					ConfigItem.class, page.getResult());
			rows = page.getTotalItems();
		}else {
			List<Config> configList = configDao.find("from Config order by id desc", values);
			configs = BeanUtils.copyListProperties(
					ConfigItem.class, configList);
			rows = configs.size();
		}
		results.put("total", rows);
		results.put("rows", configs);
		return results;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2015-01-14 21:25:22
	 * @modified by
	 * @param item
	 * @return
	 */
	public boolean saveOrUpdateConfig(ConfigItem item) {
		Config config = null;
		if (item.getId()==null) {
			config = new Config();
		}else {
			config = configDao.get(item.getId());
		}
		//复制前台修改的属性
		BeanUtils.copyProperties(config, item);
		configDao.save(config);
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2015-01-14 21:25:22
	 * @modified by
	 * @param configId
	 * @return
	 */
	public ConfigItem loadConfigItem(Long configId) {
		Config config = configDao.get(configId);
		ConfigItem configItem = new ConfigItem();
		BeanUtils.copyProperties(configItem, config);
		return configItem;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2015-01-14 21:25:22
	 * @modified by
	 * @param id
	 * @return
	 */
	public boolean deleteConfigInfo(long id) {
		delete(id);
		return true;
	}
	
	/**
	 * @description
	 * @author max.zheng
	 * @create 2015-01-14 21:25:22
	 * @modified by
	 * @param newIds
	 * @return
	 */
	public boolean deleteConfigInfo(List<Long> configIds) {
		for (Long id : configIds) {
			delete(id);
		}
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2015-1-17下午1:27:39
	 * @modified by
	 * @param pg
	 * @param reportId
	 * @return
	 */
	public Map<String, Object> loadConfigReportPage(Pagination pg, Long reportId) {
		Config config = get(reportId);
		String sql = config.getConfigSql();
//		Object obj = configDao.excuteSql(sql);
//		Page page = baseDao.findPageBySql(pg, sql, null);
//		Map<String, Object> results = new HashMap<String, Object>();
//		JSONArray array = new JSONArray();
//		List rs =  page.getResult();
//		for (Object robj : rs) {
//			Object[] tempRs = (Object[]) robj;
//			JSONObject obj = new JSONObject();
//			for (int i = 0; i < tempRs.length; i++) {
//				Object object = tempRs[i];
//				obj.put(keyString, map.get(keyString));
//			}
//		}
		Page page = baseDao.excuteQuerySqlByJdbc(pg,sql);
		JSONArray array = new JSONArray();
		List<Map<String, Object>> rs = page.getResult();
		for (Map<String, Object> map : rs)
		{
			JSONObject obj = new JSONObject();
			for (String keyString : map.keySet())
			{
				Object value = map.get(keyString);
				if (value instanceof Date || value instanceof Timestamp) {
					value = DateUtil.dateToString((Date)value, DateStyle.YYYY_MM_DD_HH_MM_SS);
				}
				obj.put(keyString, value);
			}
			array.add(obj);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", page.getTotalItems());
		result.put("rows", array);
//		results.put("total", page.getTotalItems());
//		results.put("rows", array);
		return result;
	}

}
