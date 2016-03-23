package com.xiangtai.framework.core.dao;

import com.xiangtai.framework.core.dao.domain.PageData;
import com.xiangtai.framework.core.dao.domain.Page;

import java.util.List;
import java.util.Map;

public interface MapSimpleDao {
    Object getObject(String var1);

    Object getObjectByMap(String var1, Map<String, Object> var2);

    Map<String, Object> getMap(String var1);

    Map<String, Object> getMapByMap(String var1, Map<String, Object> var2);

    List<Map<String, Object>> select(String var1);


    PageData<Map<String, Object>> selectByPage(String var1, String var2, Page var3);

    List<Map<String, Object>> selectByMap(String var1, Map<String, Object> var2);

    PageData<Map<String, Object>> selectByMapPage(String sqlId, String countSqlId, Map<String, Object> paramMap, Page page);

    int countByMap(String var1, Map<String, Object> var2);

    Object insert(String var1, Map<String, Object> var2);

    int delete(String var1, Map<String, Object> var2);

    int update(String var1, Map<String, Object> var2);
}