package com.xiangtai.framework.core.mapper;

import com.xiangtai.framework.core.entity.DictionaryFormMap;
import com.xiangtai.framework.core.entity.OrgFormMap;
import com.xiangtai.framework.core.mapper.base.BaseMapper;

import java.util.List;
import java.util.Map;

public interface DictionaryMapper extends BaseMapper {

    //根据字典类型查找字典项
    List<DictionaryFormMap> searchForCodeType(DictionaryFormMap dictionaryFormMap);
}
