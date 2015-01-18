package com.xdtech.base.service;

import java.util.List;
import java.util.Map;

import com.xdtech.base.model.Subject;
import com.xdtech.base.vo.SubjectItem;
import com.xdtech.common.service.IBaseService;

public interface SubjectService extends IBaseService<Subject>{

	boolean saveOrUpdateRecord(Map<String, List<SubjectItem>> effectRow);

}
