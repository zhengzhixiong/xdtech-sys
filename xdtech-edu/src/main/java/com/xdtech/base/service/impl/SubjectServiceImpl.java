package com.xdtech.base.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.base.dao.SubjectDao;
import com.xdtech.base.model.Subject;
import com.xdtech.base.service.SubjectService;
import com.xdtech.base.vo.SubjectItem;
import com.xdtech.common.service.BaseService;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.sys.common.ConStants;
import com.xdtech.web.model.Pagination;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService{
	@Autowired
	private SubjectDao subjectDao;
	

	
	public boolean saveOrUpdateRecord(Map<String, List<SubjectItem>> effectRow) {
		// TODO Auto-generated method stub
		//更新记录
				//新增记录
		List<Subject> inserted = voToPo(effectRow.get(ConStants.INSERTED),ConStants.INSERTED);
		//更新记录
		List<Subject> updated = voToPo(effectRow.get(ConStants.UPDATED),ConStants.UPDATED); ;
		//删除记录
		List<Subject> deleted = voToPo(effectRow.get(ConStants.DELETED),ConStants.DELETED);;
		
		inserted.addAll(updated);
		subjectDao.saveAll(inserted);
		subjectDao.deleteAll(deleted);
		return true;
	}
	private List<Subject> voToPo(List<SubjectItem> items,String actionType) {
		List<Subject> subjects = new ArrayList<Subject>();
		Subject subject = null;
		for (SubjectItem item : items) {
			subject = new Subject();
			BeanUtils.copyProperties(subject, item);
			if (ConStants.INSERTED.equals(actionType)) {
				
				
			}else if (ConStants.UPDATED.equals(actionType)) {
				//数据库获取最新记录，再更新
				subject = subjectDao.get(subject.getId());
				BeanUtils.copyProperties(subject, item);
			}else if (ConStants.DELETED.equals(actionType)) {
				
			}
			subjects.add(subject);
		}
		return subjects;
	}

	
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		// TODO Auto-generated method stub
		return null;
	}
	public void save(Subject entity) {
		subjectDao.save(entity);
	}
	public void delete(Subject entity) {
		subjectDao.delete(entity);
	}
	public void delete(Long id) {
		subjectDao.delete(id);
	}
	public Subject get(Long id) {
		return subjectDao.get(id);
	}
	public List<Subject> getAll() {
		return subjectDao.getAll();
	}

}
