package com.xdtech.stu.service;

import java.util.List;
import java.util.Map;

import com.xdtech.common.service.IBaseService;
import com.xdtech.stu.conditions.ScoreCondition;
import com.xdtech.stu.model.Score;
import com.xdtech.stu.vo.ScoreItem;

public interface ScoreService extends IBaseService<Score>{
	List<ScoreItem> loadEnteringInfo(ScoreCondition condition);

	boolean saveOrUpdateRecord(Map<String, List<ScoreItem>> effectRow);
}
