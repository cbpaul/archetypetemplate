package com.paul.archetype.service.managerRole;

import com.paul.archetype.entry.ManagerRole;
import com.paul.archetype.service.generic.GenericManager;


public interface ManagerRoleService extends GenericManager<ManagerRole, Long> {

	/**
	 * 获取角色权限
	 * 
	 * @param id
	 *            父节点ID
	 * @param roleId
	 *            角色ID
	 * @return JSON
	 */
	public String getAuthrityListJSON(Long id, Long roleId);

	/**
	 * 保存角色
	 * 
	 * @param bean
	 *            角色对象
	 * @param treeCheckbox
	 *            角色权限
	 */
	public void saveRole(ManagerRole bean, Long... treeCheckbox);
}