package com.xiangtai.framework.core.mapper;

import com.xiangtai.framework.core.entity.UserFormMap;
import com.xiangtai.framework.core.mapper.base.BaseMapper;

import java.util.List;


public interface UserMapper extends BaseMapper {

    List<UserFormMap> findUserPage(UserFormMap userFormMap);

    UserFormMap findUserById(UserFormMap userFormMap);


}
