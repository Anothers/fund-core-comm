package com.xiangtai.framework.core.mapper;

import com.xiangtai.framework.core.entity.ResFormMap;
import com.xiangtai.framework.core.entity.ResUserFormMap;
import com.xiangtai.framework.core.entity.UserGroupsFormMap;
import com.xiangtai.framework.core.mapper.base.BaseMapper;

import java.util.HashMap;
import java.util.List;

public interface ResourcesMapper extends BaseMapper {
    List<ResFormMap> findChildlists(ResFormMap map);

    List<ResFormMap> findRes(ResFormMap map);

    List<ResFormMap> findRes2(ResFormMap map);

    void updateSortOrder(List<ResFormMap> map);

    void deleteByUserIdRoleId(ResUserFormMap map);

    void deleteByRoleIdType(String roleId);

    List<ResUserFormMap> findRoleReByRoleId(UserGroupsFormMap map);

    List<ResFormMap> findUserResourcess(String userId);

    String getdataAuthorityByResKey(HashMap<String, Object> resFormMap);
}
