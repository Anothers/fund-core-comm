package com.xiangtai.framework.core.dao.domain;

import java.io.Serializable;
import java.util.List;

public class PageData<T> extends Page implements Serializable {
    private static final long serialVersionUID = -5617880060193122233L;
    private List<T> dataList;

    public PageData() {
    }

    public PageData(Page page) {
        this.currentPage = page.currentPage;
        this.pageSize = page.pageSize;
        this.recordCount = page.recordCount;
        this.pageCount = page.pageCount;
        this.firstPage = page.firstPage;
        this.lastPage = page.lastPage;
        this.nextPage = page.nextPage;
        this.prePage = page.prePage;
        this.startRecord = page.startRecord;
        this.endRecord = page.endRecord;
        this.autoCount = page.autoCount;
        this.hasNextPage = page.hasNextPage;
        this.hasPrePage = page.hasPrePage;
    }

    public PageData(List<T> dataList) {
        this.dataList = dataList;
    }

    public List<T> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("dataList.size:");
        ret.append(null == this.dataList ? "0" : Integer.valueOf(this.dataList.size()));
        ret.append(",");
        ret.append(super.toString());
        return ret.toString();
    }
}