package com.xiangtai.framework.core.mapper;

import com.xiangtai.framework.core.entity.OrgFormMap;
import com.xiangtai.framework.core.mapper.base.BaseMapper;

import java.util.List;
import java.util.Map;

public interface OrgMapper extends BaseMapper{
    List<OrgFormMap> isExist2(Map<String, String> reMap);
}
