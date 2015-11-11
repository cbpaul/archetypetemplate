package com.paul.archetype.web;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paul.archetype.service.generic.GenericManager;
import com.paul.archetype.utils.GenericsUtils;
import com.paul.archetype.utils.RequestUtils;
import com.paul.archetype.utils.ServiceUtils;
import com.paul.archetype.utils.StringUtils;


/**
 * @文件名 BaseController.java
 * @作者 paul
 * @创建日期 2014-2-14
 * @版本 V 1.0
 */
public abstract class BaseController<T> {
	protected Class<T> entityClass;
	protected String module;
	protected String lowerModule;
	protected String moduleTableName;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Writer writer;
	@ModelAttribute
	public void setRequestAndResponse(HttpServletRequest request,HttpServletResponse response) throws IOException{
		this.request =request;
		this.response=response;
		this.writer = response.getWriter();
	}

	@SuppressWarnings("unchecked")
	public BaseController() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
		module = entityClass.getSimpleName();
		lowerModule = StringUtils.toLowerCaseFirstOne(module);
		moduleTableName = getTableName(entityClass);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void delete(Writer writer, Integer hidden, Long... id) throws IOException {
		try {
			getManager().remove(id);
			writer.write(ServiceUtils.errorMVC(200, "删除成功"));
		} catch (Exception e) {
			writer.write(ServiceUtils.errorMVC(500, "删除失败"));
		}
		
		
	}


	@RequestMapping(value = "/list")
	public String list(Model model,Integer perPageItems,Integer pageIndex) {
		perPageItems=perPageItems==null?10:perPageItems;
		pageIndex=pageIndex == null?0:pageIndex-1;
		Map<String, Object> searchParams = RequestUtils
				.getParametersStartingWith(request, "search_");
		String derection = "desc";
		String field = request.getParameter("asc");
		if (field != null) {
			derection = "asc";
		} else {
			field = request.getParameter("desc");
			if (field == null) {
				field = "id";
			}
		}
		Page<T> page = getManager().findPage(
				pageIndex,perPageItems,derection , searchParams,
				field);
		setCommonModel(model);
		setListCommonModel(model);
		model.addAttribute("page", page);
		model.addAttribute("search", searchParams);
		return lowerModule + "/" + lowerModule + "List";
	}

	@RequestMapping(value = { "/edit", "/add" }, method = RequestMethod.GET)
	public String goEdit(Model model, Long id) {
		if (null != id) {
			T bean = getManager().get(id);
			if (bean != null) {
				model.addAttribute("bean", bean);
			}
		}
		setCommonModel(model);
		setEditCommonModel(model, id);
		return lowerModule + "/" + lowerModule + "Edit";
	}

	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String goShow(Model model, Long id,String returnAction) {
		model.addAttribute("returnAction", returnAction);
		if (null != id) {
			T bean = getManager().get(id);
			if (bean != null) {
				model.addAttribute("bean", bean);
			}
		}
		setCommonModel(model);
		setEditCommonModel(model, id);
		return lowerModule + "/" + lowerModule + "Show";
	}

	protected abstract GenericManager<T, Long> getManager();

	protected abstract void setListCommonModel(Model model);

	protected abstract void setEditCommonModel(Model model, Long id);

	public void setCommonModel(Model model) {
		model.addAttribute("base", "controller/" + this.module);
	}

	private String getTableName(Class clazz) {
		Table annotation = (Table) clazz.getAnnotation(Table.class);
		if (annotation != null) {
			return annotation.name();
		}
		return null;
	}

}
