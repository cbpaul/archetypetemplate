package com.paul.archetype.service.manager;

import java.util.List;

import org.springframework.ui.Model;

import com.paul.archetype.entry.Manager;
import com.paul.archetype.entry.ManagerRole;
import com.paul.archetype.service.generic.GenericManager;
import com.paul.archetype.service.manager.impl.AuthDTO;


public interface ManagerService extends GenericManager<Manager, Long> {
	/**
	 * @author paul 后台用户登录业务逻辑处理
	 * @param account
	 * @param password
	 * @return
	 */
	public Manager login(String account, String password, Model mav);

	/**
	 * 根据角色得到用户可用菜单
	 * 
	 * @param roleId
	 */
	public List<AuthDTO> getRoleAvailableMenu(Long roleId, Integer... types);

	/**
	 * 保存帐号信息(新增时设置创建时间、hidden=0、密码明码加密；修改时设置已有的加密密码)
	 * 
	 * @param bean
	 */
	public void saveManager(Manager bean);

	/**
	 * 检验系统是否允许使用该登录帐号
	 * 
	 * @param id
	 *            当前账户ID
	 * @param loginName
	 *            需要校验的帐号
	 * @return true：可以使用，false：不可使用
	 */
	public Boolean validLoginName(String id, String loginName);

	/**
	 * 检验系统是否允许使用该手机号码
	 * 
	 * @param id
	 *            当前账户ID
	 * @param mobile
	 *            需要校验的手机号
	 * @return true：可以使用，false：不可使用
	 */
	public Boolean validMobile(String id, String mobile);

	/**
	 * 重新加载角色的权限
	 * 
	 * @param role
	 */
	public void reloadAuth(ManagerRole role);

}