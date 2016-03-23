package com.xiangtai.framework.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangde on 16/2/28. 下午3:42
 */
public class OrgObjectImpl implements TreeObject {


    private Integer org_id;
    private Integer up_org_id;
    private String org_code;
    private String org_name;
    private String org_type;
    private String description;

    public List<TreeObject> getChildren() {
        return children;
    }

    private List<TreeObject> children = new ArrayList<>();


    public Integer getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }

    public Integer getUp_org_id() {
        return up_org_id;
    }

    public void setUp_org_id(Integer up_org_id) {
        this.up_org_id = up_org_id;
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getOrg_type() {
        return org_type;
    }

    public void setOrg_type(String org_type) {
        this.org_type = org_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getParentId() {
        return this.up_org_id;
    }

    @Override
    public void setChildren(List<TreeObject> childList) {
        this.children = childList;
    }

    @Override
    public Integer getId() {
        return org_id;
    }

    @Override
    public String getName() {
        return org_name;
    }

    @Override
    public void setName(String name) {
        this.org_name = name;
    }
}

