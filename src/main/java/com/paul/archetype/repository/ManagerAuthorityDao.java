package com.paul.archetype.repository;

import java.util.List;

import com.paul.archetype.entry.ManagerAuthority;


/**
 * @author suyin
 */
public interface ManagerAuthorityDao extends
		BaseRepository<ManagerAuthority, Long> {

	public List<ManagerAuthority> findByParentIdAndShowType(Long parentId,
			Integer showType);
}