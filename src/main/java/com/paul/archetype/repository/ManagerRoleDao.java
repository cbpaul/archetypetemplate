package com.paul.archetype.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.paul.archetype.entry.ManagerAuthority;
import com.paul.archetype.entry.ManagerRole;

/**
 * @author suyin
 */
public interface ManagerRoleDao extends BaseRepository<ManagerRole, Long> {
	/**
	 * 根据角色id得到角色对应的权限
	 * @param roleId
	 * @return
	 */
	@Query("select a from  ManagerAuthority  a inner join a.roles b where  b.id=?1 and a.showType in(?2)")
	public List<ManagerAuthority> findByRoleId(Long roleId,List<Integer> type);
	
	/**
	 * 得到所有显示权限
	 * @param roleId
	 * @return
	 */
	@Query("select a from  ManagerAuthority  a  where a.showType=1")
	public List<ManagerAuthority> findAllShowAuth();
}