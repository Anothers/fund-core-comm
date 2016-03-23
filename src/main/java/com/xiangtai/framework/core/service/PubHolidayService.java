package com.xiangtai.framework.core.service;

/**
 * 类描述：假期维护
 * 创建人：范兴乾
 * 创建时间：2016-3-4 下午2:27:40
 */
public interface PubHolidayService {
    String addDayByWorkDate(String date, int day) throws Exception;
}
