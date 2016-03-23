package com.xiangtai.framework.core.util;

import java.util.List;

/**
 * 封装树形转换接口
 * Created by zhangde on 16/2/28.
 */
public interface TreeObject {

    Integer getParentId();

    void setChildren(List<TreeObject> childList);

    Integer getId();

    String getName();

    void setName(String name);
}
