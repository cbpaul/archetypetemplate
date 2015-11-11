/**
* @包名：   com.sam.web.manager
* @文件名： AccountController.java
* @描述: TODO
* @作者：   paul 
* @创建日期： 2014-2-12
* @版本： V 1.0
*/
package com.paul.archetype.web.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paul.archetype.entry.Manager;
import com.paul.archetype.service.manager.ManagerService;
import com.paul.archetype.utils.RequestUtils;
import com.paul.archetype.web.Constants;

/**
 * @包名   com.sam.web.manager
 * @文件名 AccountController.java
 * @作者   paul
 * @创建日期 2014-2-12
 * @版本 V 1.0
 */
@Controller
public class AccountController {
	@Autowired
	private ManagerService managerService;
	/**
	 * 登录后台
	 * @param account 账号
	 * @param password 密码
	 * @param request
	 * @param redirectAttributes
	 * @param mav
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(String account,String password,Integer remember,HttpServletRequest request,HttpServletResponse response,Model mav){
		
		Manager manager = managerService.login(account, password, mav);
		if(manager==null){
			mav.addAttribute("account", account);
			mav.addAttribute("password", password);
			mav.addAttribute("remember", 1);
			return "login";
		}
		if(remember!=null&&remember==1){//记住我处理cookies
			RequestUtils.setCookie(response, "account", account,"account");
			RequestUtils.setCookie(response, "password", manager.getPassword(), "account");
		}else{
			RequestUtils.deleteCookie(response, RequestUtils.getCookie(request, "account"), "account");
			RequestUtils.deleteCookie(response, RequestUtils.getCookie(request, "password"), "account");
		}
		//存储session
		HttpSession session=request.getSession();
		session.setAttribute(Constants.SESSION_UID, manager.getId());
		session.setAttribute(Constants.SESSION_USER, manager.getLoginName());
		session.setAttribute(Constants.USER_ROLE_ID, manager.getRole().getId());
		return "redirect:/";
	}
	/**
	 * 注销当前用户
	 * @param session
	 * @return
	 */
	@RequestMapping(value="logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "main";
	}
	
}
