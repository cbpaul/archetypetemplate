package com.paul.archetype.service.managerAuthority;

import java.util.List;
import java.util.Map;

import com.paul.archetype.entry.ManagerAuthority;
import com.paul.archetype.service.generic.GenericManager;


public interface ManagerAuthorityService extends
		GenericManager<ManagerAuthority, Long> {

	/**
	 * 通过父ID获取子权限节点数据
	 * 
	 * @param parentId
	 *            父ID
	 * @return 权限节点集合
	 */
	List<Map<String, Object>> listAuthrityMap(Long parentId);

	/**
	 * 通过父ID获取展示类型获取数据
	 * 
	 * @param authorId
	 *            父ID
	 * @param showType
	 *            显示类型
	 * @return
	 */
	List<ManagerAuthority> listByParentIdAndShowType(Long parentId,
			Integer showType);

}