package com.paul.archetype.service.managerRoleAuthor;

import com.paul.archetype.entry.ManagerRoleAuthor;
import com.paul.archetype.service.generic.GenericManager;



public interface ManagerRoleAuthorService extends
		GenericManager<ManagerRoleAuthor, Long> {

	public Long isExist(Long roleId, Long authorId);

	public void deleteRoleAuthor(Long roleId);

	public void saveRoleAuthor(Long authorId, Long roleId);
}