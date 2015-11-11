/**
 * @文件名： MainController.java
 * @描述: TODO
 * @作者：   paul 
 * @创建日期： 2014-2-12
 * @版本： V 1.0
 */
package com.paul.archetype.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paul.archetype.utils.RequestUtils;

/**
 * @文件名 MainController.java
 * @作者 paul
 * @创建日期 2014-2-12
 * @版本 V 1.0
 */
@Controller
public class IndexController {

	@RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
	public String index(HttpSession session, Model mav,
			HttpServletRequest request) {
		if (session.getAttribute(Constants.SESSION_UID) != null) {
			return "main";
		} else {
			if (RequestUtils.getCookie(request, "account") != null) {
				mav.addAttribute("account", RequestUtils.getCookie(request, "account").getValue());
				mav.addAttribute("password", RequestUtils.getCookie(request, "password").getValue());
				mav.addAttribute("remember", 1);
			}
			return "login";
		}
	}
}
