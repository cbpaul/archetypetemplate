/**
* @文件名： MenuTreeTag.java
* @描述: TODO
* @作者：   paul 
* @创建日期： 2014-2-12
* @版本： V 1.0
*/
package com.paul.archetype.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.paul.archetype.service.manager.ManagerService;
import com.paul.archetype.service.manager.impl.AuthDTO;
import com.paul.archetype.web.Constants;


/**
 * @包名   
 * @文件名 MenuTreeTag.java
 * @作者   paul
 * @创建日期 2014-2-12
 * @版本 V 1.0
 */
public class MenuTreeTag extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	 public int doStartTag() throws JspException {
			ManagerService managerService = (ManagerService) WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext()).getBean("managerServiceImpl");
	       	Long roleId=(Long)pageContext.getSession().getAttribute(Constants.USER_ROLE_ID);
	        List<AuthDTO> menus=managerService.getRoleAvailableMenu(roleId,1);
	       try {
			Writer writer=pageContext.getOut();
			 StringBuffer sb=new StringBuffer();
			 genarateTree(menus,sb);
		     writer.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        return super.doStartTag();
	  }
	
	
	private void genarateTree(List<AuthDTO> menus,StringBuffer buffer){
		for(AuthDTO authDTO :menus){
			buffer.append("<li>");
			List<AuthDTO> childs = authDTO.getChilderns();
			if(childs !=null&& childs.size()>0){
				buffer.append("<a href='javascript:;' class='dropdown-toggle'><i class='"+authDTO.getIconCss()+"'></i><span class='menu-text'>"+authDTO.getAuthorityName()+" </span><b class='arrow icon-angle-down'></b></a><ul class='submenu'>");
				genarateTree(childs, buffer);
				buffer.append("</ul>");
				buffer.append("</li>");
			}else{
				buffer.append("<a class='ajaxDef' href='"+authDTO.getAuthorityLink()+"'><i class='"+authDTO.getIconCss()+"'></i><span class='menu-text'>"+authDTO.getAuthorityName()+" </span></a></li>");
			}
		}
		
		
	}
	
}
