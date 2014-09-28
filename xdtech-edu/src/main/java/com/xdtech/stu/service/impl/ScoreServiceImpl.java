package com.xdtech.stu.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.base.dao.GradeDao;
import com.xdtech.base.dao.SubjectDao;
import com.xdtech.core.dao.BaseDao;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.stu.conditions.ScoreCondition;
import com.xdtech.stu.dao.ScoreDao;
import com.xdtech.stu.dao.StudentDao;
import com.xdtech.stu.dao.StudentGradeDao;
import com.xdtech.stu.model.Score;
import com.xdtech.stu.service.ScoreService;
import com.xdtech.stu.vo.ScoreItem;
import com.xdtech.sys.common.ConStants;
import com.xdtech.web.model.Pagination;
@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private StudentGradeDao studentGradeDao;
	@Autowired
	private GradeDao gradeDao;
	@Autowired
	private ScoreDao scoreDao;
	@Autowired
	private SubjectDao subjectDao;
	

	
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ScoreItem> loadEnteringInfo(ScoreCondition condition) {
//		baseDao.findPageByNamedQuery(pg, "student.getByCondition", values);
		
//		List<Student> students = studentDao.getStudentsWithGrade(item.getGradeId());
		List<ScoreItem> items = baseDao.findByNamedQuery("student.getStudentScore", condition);
//		ScoreItem scoreItem = null;
//		List<Object[]> results = baseDao.findByNamedQuery("student.getStudentScore", condition.getQueryConditions());
//		for (Object[] rs : results) {
//			scoreItem = new ScoreItem();
//			scoreItem.setGradeId(condition.getGradeId());
//			scoreItem.setSubjectId(condition.getSubjectId());
//			scoreItem.setStudentId(((BigInteger)rs[0]).longValue());
//			scoreItem.setStudentNo((String)rs[1]);
//			scoreItem.setStudentName((String)rs[2]);
//			scoreItem.setPoint((Double)rs[3]);
//			scoreItem.setId(rs[4]!=null?((BigInteger)rs[4]).longValue():null);
//			items.add(scoreItem);
//		}
		return items;
	}
	
	public boolean saveOrUpdateRecord(Map<String, List<ScoreItem>> effectRow) {
		//更新记录
		//新增记录
		List<Score> inserted = voToPo(effectRow.get(ConStants.INSERTED),ConStants.INSERTED);
		//更新记录
		List<Score> updated = voToPo(effectRow.get(ConStants.UPDATED),ConStants.UPDATED); ;
		//删除记录
		List<Score> deleted = voToPo(effectRow.get(ConStants.DELETED),ConStants.DELETED);;
		
		inserted.addAll(updated);
		scoreDao.saveAll(inserted);
		scoreDao.deleteAll(deleted);
		return true;
	}
	private List<Score> voToPo(List<ScoreItem> items,String actionType) {
		List<Score> scores = new ArrayList<Score>();
		Score score = null;
		for (ScoreItem item : items) {
			score = new Score();
			BeanUtils.copyProperties(score, item);
			if (ConStants.INSERTED.equals(actionType)) {
				
				
			}else if (ConStants.UPDATED.equals(actionType)) {
				//这个更新标记只是针对前台数据列表里是更新动作而已
				//数据库获取最新记录，再更新
				if (score.getId()!=null) {
					score = scoreDao.get(score.getId());
				}else {
					//避免空
					score.setStudent(studentDao.get(item.getStudentId()));
					score.setSubject(subjectDao.get(item.getSubjectId()));
				}
				BeanUtils.copyProperties(score, item);
			}else if (ConStants.DELETED.equals(actionType)) {
				
			}
			scores.add(score);
		}
		return scores;
	}

	public void save(Score entity) {
		scoreDao.save(entity);
	}

	public void delete(Score entity) {
		scoreDao.delete(entity);
	}

	public void delete(Long id) {
		scoreDao.delete(id);
	}

	public Score get(Long id) {
		return scoreDao.get(id);
	}

	public List<Score> getAll() {
		return scoreDao.getAll();
	}
	
}
