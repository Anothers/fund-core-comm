package com.xiangtai.framework.core.controller.system;

import com.xiangtai.framework.core.controller.index.BaseController;
import com.xiangtai.framework.core.entity.UserLoginFormMap;
import com.xiangtai.framework.core.mapper.UserLoginMapper;
import com.xiangtai.framework.core.plugin.PageView;
import com.xiangtai.framework.core.util.Common;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 *
 * @author xiangtai 2016-02-25
 * @version 1.0v
 */
@Controller
@RequestMapping("/userlogin/")
public class UserLoginController extends BaseController {
	@Inject
	private UserLoginMapper userLoginMapper;

	@RequestMapping("listUI")
	public String listUI() throws Exception {
		return Common.BACKGROUND_PATH + "/system/userlogin/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		UserLoginFormMap userLoginFormMap = getFormMap(UserLoginFormMap.class);
		userLoginFormMap=toFormMap(userLoginFormMap, pageNow, pageSize,userLoginFormMap.getStr("orderby"));
        pageView.setRecords(userLoginMapper.findByPage(userLoginFormMap));
		return pageView;
	}

}