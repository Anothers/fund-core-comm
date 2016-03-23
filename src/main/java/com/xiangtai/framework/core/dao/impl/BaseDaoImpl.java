package com.xiangtai.framework.core.dao.impl;

import com.xiangtai.framework.core.dao.BaseDao;
import com.xiangtai.framework.core.dao.MapSimpleDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangde on 16/2/26. 下午3:14
 */
public abstract class BaseDaoImpl implements BaseDao {
    @Autowired
    public MapSimpleDao mapSimpleDao;
}
