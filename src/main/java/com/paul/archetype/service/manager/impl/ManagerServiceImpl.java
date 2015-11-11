package com.paul.archetype.service.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.paul.archetype.entry.Manager;
import com.paul.archetype.entry.ManagerAuthority;
import com.paul.archetype.entry.ManagerRole;
import com.paul.archetype.repository.ManagerDao;
import com.paul.archetype.repository.ManagerRoleDao;
import com.paul.archetype.service.generic.impl.GenericManagerImpl;
import com.paul.archetype.service.manager.ManagerService;
import com.paul.archetype.utils.MD5Utils;
import com.paul.archetype.utils.ObjectUtils;



/**
 * @文件名： managerServiceImpl.java
 * @描述: TODO
 * @作者： paul
 * @创建日期：
 * @版本： V 1.0
 */
@Component
@Transactional
public class ManagerServiceImpl extends GenericManagerImpl<Manager, Long>
		implements ManagerService {
	/**
	 * 角色对应权限初始化
	 */
	public static Map<Long, Set<ManagerAuthority>> roleAuthMap = new HashMap<Long, Set<ManagerAuthority>>();

	/**
	 * 角色对应菜单列表
	 */
	public static Map<Long, List<AuthDTO>> roleAuthMenuMap = new HashMap<Long, List<AuthDTO>>();
	private ManagerDao managerDao;
	@Autowired
	private ManagerRoleDao roleDao;

	@Autowired
	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
		this.dao = managerDao;
	}

	public Manager login(String account, String password, Model mav) {
		if (ObjectUtils.anyIsEmpty(account, password)) {
			mav.addAttribute("error", "登陆名和密码必须输入。");
			return null;
		}
		if(password.length()!=32){
			password = MD5Utils.MD5(password);
		}
		Manager manager = managerDao.findByLoginNameAndPassword(account,
				password);
		if (manager == null) {
			mav.addAttribute("error", "用户名或密码错误");
			return null;
		}
		ManagerRole role = manager.getRole();// 用户角色
		if (role != null) {// 缓存角色对应权限
			Set<ManagerAuthority> auth = roleAuthMap
					.get(role.getId());
			if (auth == null) {
				reloadAuth(role);
			}
		}
		return manager;
	}

	public void reloadAuth(ManagerRole role) {
		Set<ManagerAuthority> auth = new HashSet<ManagerAuthority>();
		List<ManagerAuthority> authorities = roleDao.findByRoleId(role.getId(),
				Arrays.asList(new Integer[] { 0, 1, 2 }));
		auth.addAll(authorities);
		roleAuthMap.put(role.getId(), auth);
	}

	/**
	 * 得到角色可用菜单
	 */
	public List<AuthDTO> getRoleAvailableMenu(Long roleId, Integer... types) {
		List<ManagerAuthority> authorities = null;
		if (roleId != 1L && types != null && types.length > 0) {
			authorities = roleDao.findByRoleId(roleId, Arrays.asList(types));
		} else {
			authorities = roleDao.findAllShowAuth();
		}
		List<AuthDTO> authDTOs = new ArrayList<AuthDTO>();
		List<AuthDTO> resuList = new ArrayList<AuthDTO>();
		if (authorities != null && authorities.size() > 0) {
			Iterator<ManagerAuthority> authIterator = authorities.iterator();
			while (authIterator.hasNext()) {
				ManagerAuthority authority = authIterator.next();
				if (authority.getParentId() == null
						|| authority.getParentId() == 0) {
					AuthDTO authDTO = new AuthDTO(authority.getId());
					if (!authDTOs.contains(authDTO)) {
						BeanUtils.copyProperties(authority, authDTO);
						authDTOs.add(authDTO);
					}
					authIterator.remove();
				}
			}
			if (authorities.size() != 0) {
				resuList = recurAuths(authorities, authDTOs, resuList);
			}
		}
		return resuList;
	}

	/**
	 * 生成树结构对象list
	 * 
	 * @param handlerAuths
	 * @param levelAuths
	 * @param resultAuths
	 * @return
	 */
	private List<AuthDTO> recurAuths(List<ManagerAuthority> handlerAuths,
			List<AuthDTO> levelAuths, List<AuthDTO> resultAuths) {
		if (handlerAuths != null) {
			Iterator<ManagerAuthority> authIterator = handlerAuths.iterator();
			List<AuthDTO> nextLevlAuths = new ArrayList<AuthDTO>();// 下一级列表
			while (authIterator.hasNext()) {
				ManagerAuthority authority = authIterator.next();
				AuthDTO parentAuthDTO = new AuthDTO(authority.getParentId());
				if (levelAuths.contains(parentAuthDTO)) {
					AuthDTO parentAuth = levelAuths.get(levelAuths
							.indexOf(parentAuthDTO));
					List<AuthDTO> childers = parentAuth.getChilderns();
					if (childers == null) {
						childers = new ArrayList<AuthDTO>();
						parentAuth.setChilderns(childers);
					}
					AuthDTO authDTO = new AuthDTO(authority.getId());
					BeanUtils.copyProperties(authority, authDTO);
					childers.add(authDTO);
					Collections.sort(childers);
					nextLevlAuths.add(authDTO);
					authIterator.remove();
				}
			}
			if (handlerAuths.size() > 0) {
				recurAuths(handlerAuths, nextLevlAuths, resultAuths);
			}
			for (AuthDTO auth : levelAuths) {
				if (resultAuths.contains(auth)) {
					List<AuthDTO> childList = resultAuths.get(
							resultAuths.indexOf(auth)).getChilderns();
					Collections.sort(childList);
					auth.setChilderns(childList);
				}
			}
			List<AuthDTO> newAuths = new ArrayList<AuthDTO>();
			Collections.sort(levelAuths);
			newAuths.addAll(levelAuths);
			resultAuths = newAuths;
		}
		return resultAuths;
	}

	@Override
	public void saveManager(Manager bean) {
		if (ObjectUtils.isEmpty(bean.getId())) {
			bean.setCreatedOn(new Date());
			bean.setHidden(0);
			bean.setPassword(MD5Utils.MD5(bean.getPassword()));
			managerDao.save(bean);
		} else {
			bean.setPassword(managerDao.findOne(bean.getId()).getPassword());
			managerDao.save(bean);
		}
	}

	@Override
	public Boolean validLoginName(String id, String loginName) {
		if (ObjectUtils.isNotNull(loginName)) {
			List<Manager> list = managerDao.findByLoginName(loginName);
			if (ObjectUtils.isNotEmpty(list) && list.size() > 0) {
				if (list.size() > 1) {
					return false;
				} else {
					if (ObjectUtils.isNotNull(id)) {
						Manager bean = list.get(0);
						if (bean.getId().longValue() != ObjectUtils.toLong(id)
								.longValue()) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public Boolean validMobile(String id, String mobile) {
		if (ObjectUtils.isNotNull(mobile)) {
			List<Manager> list = managerDao.findByMobile(mobile);
			if (ObjectUtils.isNotEmpty(list) && list.size() > 0) {
				if (list.size() > 1) {
					return false;
				} else {
					if (ObjectUtils.isNotNull(id)) {
						Manager bean = list.get(0);
						if (bean.getId().longValue() != ObjectUtils.toLong(id)
								.longValue()) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}
}
