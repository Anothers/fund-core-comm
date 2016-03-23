package com.xiangtai.framework.core.mapper;

import com.xiangtai.framework.core.entity.RoleFormMap;
import com.xiangtai.framework.core.mapper.base.BaseMapper;

import java.util.List;

public interface RoleMapper extends BaseMapper {
    List<RoleFormMap> seletUserRole(RoleFormMap map);

    void deleteById(RoleFormMap map);
}
