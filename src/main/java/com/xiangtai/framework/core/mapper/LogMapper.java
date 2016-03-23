package com.xiangtai.framework.core.mapper;

import com.xiangtai.framework.core.entity.LogFormMap;
import com.xiangtai.framework.core.mapper.base.BaseMapper;

import java.util.List;

/**
 * @author zhangde
 * @date 2016-02-28
 */
public interface LogMapper extends BaseMapper{
    List<LogFormMap> findLogPage(LogFormMap logFormMap);
}
