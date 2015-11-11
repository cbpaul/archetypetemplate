package com.paul.archetype.web.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paul.archetype.entry.Manager;
import com.paul.archetype.service.generic.GenericManager;
import com.paul.archetype.service.manager.ManagerService;
import com.paul.archetype.web.BaseController;

/**
 * @包名   com.paul.archetype.web.manager
 * @文件名 ManagerController.java
 * @作者   paul
 * @创建日期 2014-12-11
 * @版本 V 1.0
 */
@Controller
@RequestMapping("controller/Manager")
public class ManagerController extends BaseController<Manager> {
	@Autowired
	private ManagerService manager;
	/* (non-Javadoc)
	 * @see com.paul.archetype.web.BaseController#getManager()
	 */
	@Override
	protected GenericManager<Manager, Long> getManager() {
		// TODO Auto-generated method stub
		return manager;
	}

	/* (non-Javadoc)
	 * @see com.paul.archetype.web.BaseController#setListCommonModel(org.springframework.ui.Model)
	 */
	@Override
	protected void setListCommonModel(Model model) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.paul.archetype.web.BaseController#setEditCommonModel(org.springframework.ui.Model, java.lang.Long)
	 */
	@Override
	protected void setEditCommonModel(Model model, Long id) {
		// TODO Auto-generated method stub
		
	}

}
