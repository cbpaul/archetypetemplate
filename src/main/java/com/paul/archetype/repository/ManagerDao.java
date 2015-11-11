package com.paul.archetype.repository;

import java.util.List;

import com.paul.archetype.entry.Manager;


/**
 * @author suyin
 */
public interface ManagerDao extends BaseRepository<Manager, Long> {
	/**
	 * 通过登录名与密码找到后台用户
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	Manager findByLoginNameAndPassword(String loginName, String password);

	/**
	 * 通过登录帐号获取账户信息
	 * 
	 * @param loginName
	 * @return
	 */
	List<Manager> findByLoginName(String loginName);

	/**
	 * 通过手机号码获取账户信息
	 * 
	 * @param mobile
	 * @return
	 */
	List<Manager> findByMobile(String mobile);
}