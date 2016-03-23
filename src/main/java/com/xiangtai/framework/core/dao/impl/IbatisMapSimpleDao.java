package com.xiangtai.framework.core.dao.impl;

import java.util.List;
import java.util.Map;

import com.xiangtai.framework.core.dao.MapSimpleDao;
import com.xiangtai.framework.core.dao.domain.PageData;
import com.xiangtai.framework.core.dao.domain.Page;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IbatisMapSimpleDao implements MapSimpleDao {
    @Autowired(required = true)
    protected SqlSession sqlSessionTemplate;

    @Override
    public Object getObject(String sqlId) {
        return this.sqlSessionTemplate.selectList(sqlId);
    }

    @Override
    public Object getObjectByMap(String sqlId, Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(sqlId, paramMap);
    }

    @Override
    public Map<String, Object> getMap(String sqlId) {
        return (Map<String, Object>) this.getObject(sqlId);
    }

    @Override
    public Map<String, Object> getMapByMap(String sqlId, Map<String, Object> paramMap) {
        return (Map<String, Object>) this.getObjectByMap(sqlId, paramMap);
    }


    @Override
    public List<Map<String, Object>> select(String sqlId) {
        return this.sqlSessionTemplate.selectList(sqlId);
    }

    @Override
    public PageData<Map<String, Object>> selectByPage(String sqlId, String countSqlId, Page page) {
        return this.selectByMapPage(sqlId, countSqlId, (Map) null, page);
    }

    @Override
    public List<Map<String, Object>> selectByMap(String sqlId, Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.selectList(sqlId, paramMap);
    }

    @Override
    public PageData<Map<String, Object>> selectByMapPage(String sqlId, String countSqlId, Map<String, Object> paramMap, Page page) {
        if (page.isAutoCount()) {
            page.setRecordCount(this.countByMap(countSqlId, paramMap));
        } else {
            page.setRecordCount(page.getRecordCount());
        }

        int skip = page.getStartRecord();
        int max = page.getPageSize();

        PageData pageData = new PageData(page);
        RowBounds rowbounds = new RowBounds(skip, max);
        pageData.setDataList(this.sqlSessionTemplate.selectList(sqlId, countSqlId, rowbounds));
        return pageData;
    }

    @Override
    public int countByMap(String countSqlId, Map<String, Object> paramMap) {
        Integer total = (Integer) this.sqlSessionTemplate.selectList(countSqlId, paramMap).get(0);
        return total.intValue();
    }

    @Override
    public Object insert(String sqlId, Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.insert(sqlId, paramMap);
    }

    @Override
    public int delete(String sqlId, Map<String, Object> paramMap) {
        return this.sqlSessionTemplate.delete(sqlId, paramMap);
    }

    @Override
    public int update(String sqlId, Map<String, Object> paramMap) {
        return this.update(sqlId, paramMap);
    }
}
