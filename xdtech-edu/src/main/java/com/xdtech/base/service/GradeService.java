package com.xdtech.base.service;

import java.util.List;
import java.util.Map;

import com.xdtech.base.model.Grade;
import com.xdtech.base.vo.GradeItem;
import com.xdtech.common.service.IBaseService;

public interface GradeService extends IBaseService<Grade>{

	boolean saveOrUpdateRecord(Map<String, List<GradeItem>> effectRow);

}
