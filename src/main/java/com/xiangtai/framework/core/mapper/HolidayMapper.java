package com.xiangtai.framework.core.mapper;

import java.util.List;

import com.xiangtai.framework.core.entity.HolidayFormMap;
import com.xiangtai.framework.core.mapper.base.BaseMapper;


/**
 * 类描述：假期维护
 * 创建人：范兴乾
 * 创建时间：2016-3-4 下午2:10:36
 */
public interface HolidayMapper extends BaseMapper{
	 List<HolidayFormMap> findHoliday(HolidayFormMap holidayMap);
}
