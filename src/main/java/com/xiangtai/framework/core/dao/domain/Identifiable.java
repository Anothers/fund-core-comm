package com.xiangtai.framework.core.dao.domain;

import java.io.Serializable;

/**
 * 主键标识
 *
 * @author ZhangDe
 * @date 2015年9月26日下午5:55:34
 */
public interface Identifiable extends Serializable {
    /**
     * 获取主键
     *
     * @return
     * @author ZhangDe
     * @date 2015年9月26日下午5:56:13
     */
    String getId();

    /**
     * 设置ID属性
     *
     * @param id
     */
    void setId(String id);
}
