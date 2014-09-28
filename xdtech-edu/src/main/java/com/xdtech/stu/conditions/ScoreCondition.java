package com.xdtech.stu.conditions;

import com.xdtech.core.model.BaseCondition;
import com.xdtech.stu.vo.ScoreItem;

public class ScoreCondition extends BaseCondition{
	private Long gradeId;
	
	private Long subjectId;
	
	

	public ScoreCondition() {
		super();
		setBaseItem(new ScoreItem());
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
		addToMap("gradeId", gradeId.toString());
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
		addToMap("subjectId", subjectId.toString());
	}
	
	
}
