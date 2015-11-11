package com.paul.archetype.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.paul.archetype.entry.ManagerRoleAuthor;


/**
 * @author suyin
 */
public interface ManagerRoleAuthorDao extends
		BaseRepository<ManagerRoleAuthor, Long> {

	@Query("select count(a.id) from ManagerRoleAuthor a where a.roleId=?1 and a.authorId=?2")
	public Long isExist(Long roleId, Long authorId);

	@Modifying
	@Query("delete ManagerRoleAuthor a where a.roleId=?1")
	public void deleteRoleAuthor(Long roleId);
}