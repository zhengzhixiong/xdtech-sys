package com.xdtech.message.service;

import com.xdtech.common.service.IBaseService;
import com.xdtech.message.model.Person;
import com.xdtech.message.vo.PersonItem;

/**
 * 
 * @author max.zheng
 * @create 2014-11-02 00:15:20
 * @since 1.0
 * @see
 */
public interface PersonService extends IBaseService<Person>{

	/**
	 * 保存更新信息
	 * @author max.zheng
	 * @create 2014-11-02 00:15:20
	 * @modified by
	 * @param item
	 * @return
	 */
	boolean saveOrUpdatePerson(PersonItem item);

	/**
	 * 加载记录信息
	 * @author max.zheng
	 * @create 2014-11-02 00:15:20
	 * @modified by
	 * @param newId
	 * @return
	 */
	PersonItem loadPersonItem(Long personId);

	/**
	 * 根据id号删除记录信息
	 * @author max.zheng
	 * @create 2014-11-02 00:15:20
	 * @modified by
	 * @param id
	 * @return
	 */
	boolean deletePersonInfo(long id);

}
