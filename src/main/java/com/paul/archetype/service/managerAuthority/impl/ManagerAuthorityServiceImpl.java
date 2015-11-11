package com.paul.archetype.service.managerAuthority.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.paul.archetype.entry.ManagerAuthority;
import com.paul.archetype.repository.ManagerAuthorityDao;
import com.paul.archetype.repository.ManagerAuthorityTemplateDao;
import com.paul.archetype.service.generic.impl.GenericManagerImpl;
import com.paul.archetype.service.managerAuthority.ManagerAuthorityService;


/**
 * @文件名： managerAuthorityServiceImpl.java
 * @描述: TODO
 * @作者： paul
 * @创建日期：
 * @版本： V 1.0
 */
@Component
@Transactional
public class ManagerAuthorityServiceImpl extends
		GenericManagerImpl<ManagerAuthority, Long> implements
		ManagerAuthorityService {
	private ManagerAuthorityDao managerAuthorityDao;
	private ManagerAuthorityTemplateDao managerAuthorityTemplateDao;

	@Autowired
	public void setManagerAuthorityDao(ManagerAuthorityDao managerAuthorityDao) {
		this.managerAuthorityDao = managerAuthorityDao;
		this.dao = managerAuthorityDao;
	}

	@Autowired
	public void setManagerAuthorityTemplateDao(
			ManagerAuthorityTemplateDao managerAuthorityTemplateDao) {
		this.managerAuthorityTemplateDao = managerAuthorityTemplateDao;
	}

	@Override
	public List<Map<String, Object>> listAuthrityMap(Long parentId) {
		return managerAuthorityTemplateDao.listAuthrityMap(parentId);
	}

	@Override
	public List<ManagerAuthority> listByParentIdAndShowType(Long parentId,
			Integer showType) {
		return managerAuthorityDao.findByParentIdAndShowType(parentId,showType);
	}

}