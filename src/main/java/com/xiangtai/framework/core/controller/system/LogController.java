package com.xiangtai.framework.core.controller.system;

import com.xiangtai.framework.core.controller.index.BaseController;
import com.xiangtai.framework.core.entity.LogFormMap;
import com.xiangtai.framework.core.mapper.LogMapper;
import com.xiangtai.framework.core.plugin.PageView;
import com.xiangtai.framework.core.util.Common;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * 
 * @author xiangtai 2014-11-19
 *
 * @version 3.0v
 */
@Controller
@RequestMapping("/log/")
public class LogController extends BaseController {
	@Inject
	private LogMapper logMapper;

	@RequestMapping("list")
	public String listUI() throws Exception {
		return Common.BACKGROUND_PATH + "/system/log/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) throws Exception {
		LogFormMap logFormMap = getFormMap(LogFormMap.class);
		logFormMap=toFormMap(logFormMap, pageNow, pageSize,logFormMap.getStr("orderby"));
		logFormMap.put("column", column);
		logFormMap.put("sort", sort);
		pageView.setRecords(logMapper.findLogPage(logFormMap));
		return pageView;
	}
}