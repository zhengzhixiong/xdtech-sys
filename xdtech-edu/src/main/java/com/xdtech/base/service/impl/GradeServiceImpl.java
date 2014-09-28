package com.xdtech.base.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.base.dao.GradeDao;
import com.xdtech.base.model.Grade;
import com.xdtech.base.service.GradeService;
import com.xdtech.base.vo.GradeItem;
import com.xdtech.common.service.BaseService;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.sys.common.ConStants;
import com.xdtech.web.model.Pagination;

@Service
@Transactional
public class GradeServiceImpl implements GradeService{
	@Autowired
	private GradeDao gradeDao;
	

	
	public boolean saveOrUpdateRecord(Map<String, List<GradeItem>> effectRow) {
		// TODO Auto-generated method stub
		//更新记录
				//新增记录
		List<Grade> inserted = voToPo(effectRow.get(ConStants.INSERTED),ConStants.INSERTED);
		//更新记录
		List<Grade> updated = voToPo(effectRow.get(ConStants.UPDATED),ConStants.UPDATED); ;
		//删除记录
		List<Grade> deleted = voToPo(effectRow.get(ConStants.DELETED),ConStants.DELETED);;
		
		inserted.addAll(updated);
		gradeDao.saveAll(inserted);
		gradeDao.deleteAll(deleted);
		return true;
	}
	private List<Grade> voToPo(List<GradeItem> items,String actionType) {
		List<Grade> grades = new ArrayList<Grade>();
		Grade grade = null;
		for (GradeItem item : items) {
			grade = new Grade();
			BeanUtils.copyProperties(grade, item);
			if (ConStants.INSERTED.equals(actionType)) {
				
				
			}else if (ConStants.UPDATED.equals(actionType)) {
				//数据库获取最新记录，再更新
				grade = gradeDao.get(grade.getId());
				BeanUtils.copyProperties(grade, item);
			}else if (ConStants.DELETED.equals(actionType)) {
				
			}
			grades.add(grade);
		}
		return grades;
	}

	
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		// TODO Auto-generated method stub
		return null;
	}
	public void save(Grade entity) {
		gradeDao.save(entity);
	}
	public void delete(Grade entity) {
		gradeDao.delete(entity);
	}
	public void delete(Long id) {
		gradeDao.delete(id);
	}
	public Grade get(Long id) {
		return gradeDao.get(id);
	}
	public List<Grade> getAll() {
		return gradeDao.getAll();
	}

}
