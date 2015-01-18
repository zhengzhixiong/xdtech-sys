package com.xdtech.report.service;

import java.util.List;
import java.util.Map;

import com.xdtech.common.service.IBaseService;
import com.xdtech.report.model.Config;
import com.xdtech.report.vo.ConfigItem;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2015-01-14 21:25:22
 * @since 1.0
 * @see
 */
public interface ConfigService extends IBaseService<Config>{

	/**
	 * 保存更新信息
	 * @author max.zheng
	 * @create 2015-01-14 21:25:22
	 * @modified by
	 * @param item
	 * @return
	 */
	boolean saveOrUpdateConfig(ConfigItem item);

	/**
	 * 加载记录信息
	 * @author max.zheng
	 * @create 2015-01-14 21:25:22
	 * @modified by
	 * @param newId
	 * @return
	 */
	ConfigItem loadConfigItem(Long configId);

	/**
	 * 根据id号删除记录信息
	 * @author max.zheng
	 * @create 2015-01-14 21:25:22
	 * @modified by
	 * @param id
	 * @return
	 */
	boolean deleteConfigInfo(long id);

	/**
	 * 
	 * @author max.zheng
	 * @create 2015-01-14 21:25:22
	 * @modified by
	 * @param configIds
	 */
	boolean deleteConfigInfo(List<Long> configIds);

	/**
	 * 加载配置的报表数据
	 * @author max.zheng
	 * @create 2015-1-17下午1:26:57
	 * @modified by
	 * @param pg
	 * @param reportId
	 * @return
	 */
	Map<String, Object> loadConfigReportPage(Pagination pg, Long reportId);
}
