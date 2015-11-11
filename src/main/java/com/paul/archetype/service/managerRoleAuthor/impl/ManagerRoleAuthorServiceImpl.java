package com.paul.archetype.service.managerRoleAuthor.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.paul.archetype.entry.ManagerRoleAuthor;
import com.paul.archetype.repository.ManagerRoleAuthorDao;
import com.paul.archetype.service.generic.impl.GenericManagerImpl;
import com.paul.archetype.service.managerRoleAuthor.ManagerRoleAuthorService;


/**
 * @文件名： managerRoleAuthorServiceImpl.java
 * @描述: TODO
 * @作者： paul
 * @创建日期：
 * @版本： V 1.0
 */
@Component
@Transactional
public class ManagerRoleAuthorServiceImpl extends
		GenericManagerImpl<ManagerRoleAuthor, Long> implements
		ManagerRoleAuthorService {
	private ManagerRoleAuthorDao managerRoleAuthorDao;

	@Autowired
	public void setManagerRoleAuthorDao(
			ManagerRoleAuthorDao managerRoleAuthorDao) {
		this.managerRoleAuthorDao = managerRoleAuthorDao;
		this.dao = managerRoleAuthorDao;
	}

	@Override
	public Long isExist(Long roleId, Long authorId) {
		return managerRoleAuthorDao.isExist(roleId, authorId);
	}

	@Override
	public void deleteRoleAuthor(Long roleId) {
		managerRoleAuthorDao.deleteRoleAuthor(roleId);
	}

	@Override
	public void saveRoleAuthor(Long authorId, Long roleId) {
		ManagerRoleAuthor bean = new ManagerRoleAuthor();
		bean.setAuthorId(authorId);
		bean.setRoleId(roleId);
		managerRoleAuthorDao.save(bean);
	}

}