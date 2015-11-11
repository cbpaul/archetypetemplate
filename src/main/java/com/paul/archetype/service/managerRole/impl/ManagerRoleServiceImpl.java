package com.paul.archetype.service.managerRole.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.paul.archetype.entry.ManagerAuthority;
import com.paul.archetype.entry.ManagerRole;
import com.paul.archetype.repository.ManagerRoleDao;
import com.paul.archetype.service.generic.impl.GenericManagerImpl;
import com.paul.archetype.service.manager.ManagerService;
import com.paul.archetype.service.managerAuthority.ManagerAuthorityService;
import com.paul.archetype.service.managerRole.ManagerRoleService;
import com.paul.archetype.service.managerRoleAuthor.ManagerRoleAuthorService;
import com.paul.archetype.utils.ObjectUtils;


/**
 * @文件名： managerRoleServiceImpl.java
 * @描述: TODO
 * @作者： paul
 * @创建日期：
 * @版本： V 1.0
 */
@Component
@Transactional
public class ManagerRoleServiceImpl extends
		GenericManagerImpl<ManagerRole, Long> implements ManagerRoleService {
	private ManagerRoleDao managerRoleDao;
	@Autowired
	private ManagerAuthorityService authorityService;
	@Autowired
	private ManagerRoleAuthorService managerRoleAuthorService;
	@Autowired
	private ManagerService managerService;

	@Autowired
	public void setManagerRoleDao(ManagerRoleDao managerRoleDao) {
		this.managerRoleDao = managerRoleDao;
		this.dao = managerRoleDao;
	}

	@Override
	public String getAuthrityListJSON(Long id, Long roleId) {
		// 通过父ID获取子权限节点数据
		List<Map<String, Object>> listMap = authorityService
				.listAuthrityMap(id);
		if (ObjectUtils.isEmpty(listMap)) {
			return "[]";
		}
		StringBuilder json = new StringBuilder();
		for (Map<String, Object> map : listMap) {
			json.append(json.length() > 0 ? ",{" : "{");
			json.append("\"id\":\"").append(map.get("id")).append("\" ,");
			json.append("\"text\":\"").append(map.get("text")).append("\" ,");
			json.append("\"value\":\"").append(map.get("value")).append("\" ,");
			json.append("\"hasChildren\":\"")
					.append(Boolean.parseBoolean(map.get("hasChildren")
							.toString())).append("\" ,");
			json.append("\"complete\":\"")
					.append(Boolean
							.parseBoolean(map.get("complete").toString()))
					.append("\" ,");
			json.append("\"showcheck\":\"").append(true).append("\" ,");
			json.append("\"isexpand\":\"").append(false).append("\" ,");
			if (roleId == null || 0 == roleId.longValue()) {
				json.append("\"checkState\":\"").append(0).append("\"");
			} else {
				long checkState = managerRoleAuthorService.isExist(roleId,
						ObjectUtils.toLong(map.get("id")));
				json.append("\"checkState\":\"").append(checkState)
						.append("\"  ");
			}
			json.append(" }");
		}

		return json.insert(0, "[").append("]").toString();
	}

	@Override
	public void saveRole(ManagerRole bean, Long... treeCheckbox) {
		if (bean.getId() != null) {
			managerRoleAuthorService.deleteRoleAuthor(bean.getId());
		}
		managerRoleDao.save(bean);
		for (Long authorId : treeCheckbox) {
			if (authorId == 0)
				continue;
			managerRoleAuthorService.saveRoleAuthor(authorId, bean.getId());
			List<ManagerAuthority> list = authorityService
					.listByParentIdAndShowType(authorId, 0);
			for (ManagerAuthority ma : list) {
				managerRoleAuthorService.saveRoleAuthor(ma.getId(),
						bean.getId());
			}
		}
		// 重新加载权限s
		managerService.reloadAuth(bean);
	}

}