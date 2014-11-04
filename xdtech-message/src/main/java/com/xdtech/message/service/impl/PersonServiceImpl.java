package com.xdtech.message.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.message.dao.PersonDao;
import com.xdtech.message.model.Person;
import com.xdtech.message.service.PersonService;
import com.xdtech.message.vo.PersonItem;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2014-11-02 00:15:20
 * @since 1.0
 * @see
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonDao personDao;

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-02 00:15:20
	 * @modified by
	 * @param entity
	 */
	public void save(Person entity) {
		personDao.save(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-02 00:15:20
	 * @modified by
	 * @param entity
	 */
	public void delete(Person entity) {
		personDao.delete(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-02 00:15:20
	 * @modified by
	 * @param id
	 */
	public void delete(Long id) {
		personDao.delete(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-02 00:15:20
	 * @modified by
	 * @param id
	 * @return
	 */
	public Person get(Long id) {
		return personDao.get(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-02 00:15:20
	 * @modified by
	 * @return
	 */
	public List<Person> getAll() {
		return personDao.getAll();
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-02 00:15:20
	 * @modified by
	 * @param pg
	 * @param values
	 * @return
	 */
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> persons = null;
		long rows = 0;
		if (pg!=null) {
			Page<Person> page = personDao.findPage(pg,"from Person order by createTime desc", values);
			persons = BeanUtils.copyListProperties(
					PersonItem.class, page.getResult());
			rows = page.getTotalItems();
		}else {
			List<Person> personList = personDao.find("from Person order by id desc", values);
			persons = BeanUtils.copyListProperties(
					PersonItem.class, personList);
			rows = persons.size();
		}
		results.put("total", rows);
		results.put("rows", persons);
		return results;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-02 00:15:20
	 * @modified by
	 * @param item
	 * @return
	 */
	public boolean saveOrUpdatePerson(PersonItem item) {
		Person person = null;
		if (item.getId()==null) {
			person = new Person();
		}else {
			person = personDao.get(item.getId());
		}
		//复制前台修改的属性
		BeanUtils.copyProperties(person, item);
		personDao.save(person);
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-02 00:15:20
	 * @modified by
	 * @param personId
	 * @return
	 */
	public PersonItem loadPersonItem(Long personId) {
		Person person = personDao.get(personId);
		PersonItem personItem = new PersonItem();
		BeanUtils.copyProperties(personItem, person);
		return personItem;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-11-02 00:15:20
	 * @modified by
	 * @param id
	 * @return
	 */
	public boolean deletePersonInfo(long id) {
		delete(id);
		return true;
	}

}
