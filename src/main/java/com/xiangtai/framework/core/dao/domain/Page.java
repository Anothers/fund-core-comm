package com.xiangtai.framework.core.dao.domain;

import java.io.Serializable;

public class Page extends StatusData implements Serializable {
    private static final long serialVersionUID = 4418332053713380699L;
    public static final String CURRENT_PAGE_KEY = "currentPage";
    public static final String CURRENT_PAGE_CONDITION_KEY = "current_page";
    public static final String DEFAULT_CURRENT_PAGE_STR = "1";
    private static final int DEFAULT_CURRENT_PAGE = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_RECORD_COUNT = -1;
    private static final boolean DEFAULT_AUTO_COUNT = true;
    protected int currentPage = 1;
    protected int pageCount = 1;
    protected int pageSize = 10;
    protected int recordCount = -1;
    protected int startRecord = -1;
    protected int endRecord = -1;
    protected int firstPage = 1;
    protected int prePage = 1;
    protected int nextPage = 1;
    protected int lastPage = 1;
    protected boolean hasPrePage = false;
    protected boolean hasNextPage = false;
    protected boolean autoCount = true;

    public Page() {
    }

    public Page(int currentPage) {
        this.currentPage = currentPage;
    }

    public Page(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getRecordCount() {
        return this.recordCount;
    }

    public int getStartRecord() {
        return this.startRecord;
    }

    public int getEndRecord() {
        return this.endRecord;
    }

    public int getFirstPage() {
        return this.firstPage;
    }

    public int getLastPage() {
        return this.lastPage;
    }

    public int getPrePage() {
        return this.prePage;
    }

    public int getNextPage() {
        return this.nextPage;
    }

    public boolean isHasPrePage() {
        return this.hasPrePage;
    }

    public boolean isHasNextPage() {
        return this.hasNextPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
        this.refresh();
    }

    public void setAutoCount(boolean autoCount) {
        this.autoCount = autoCount;
    }

    public boolean isAutoCount() {
        return this.recordCount == -1 ? true : this.autoCount;
    }

    private void refresh() {
        this.pageCount = this.recordCount > 0 ? (this.recordCount + this.pageSize - 1) / this.pageSize : 1;
        this.currentPage = this.currentPage > this.pageCount ? this.pageCount : this.currentPage;
        this.currentPage = this.currentPage < 1 ? 1 : this.currentPage;
        this.startRecord = (this.currentPage - 1) * this.pageSize;
        this.endRecord = this.startRecord + this.pageSize - 1;
        this.prePage = this.currentPage - 1 < 1 ? 1 : this.currentPage - 1;
        this.nextPage = this.currentPage + 1 > this.pageCount ? this.pageCount : this.currentPage + 1;
        this.lastPage = this.pageCount;
        this.hasPrePage = this.prePage < this.currentPage;
        this.hasNextPage = this.nextPage > this.currentPage;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("currentPage:");
        ret.append(this.currentPage);
        ret.append(",");
        ret.append("pageCount:");
        ret.append(this.pageCount);
        ret.append(",");
        ret.append("pageSize:");
        ret.append(this.pageSize);
        ret.append(",");
        ret.append("recordCount:");
        ret.append(this.recordCount);
        ret.append(",");
        ret.append("startRecord:");
        ret.append(this.startRecord);
        ret.append(",");
        ret.append("endRecord:");
        ret.append(this.endRecord);
        ret.append(",");
        ret.append("firstPage:");
        ret.append(this.firstPage);
        ret.append(",");
        ret.append("lastPage:");
        ret.append(this.lastPage);
        ret.append(",");
        ret.append("prePage:");
        ret.append(this.prePage);
        ret.append(",");
        ret.append("nextPage:");
        ret.append(this.nextPage);
        ret.append(",");
        ret.append("hasPrePage:");
        ret.append(this.hasPrePage);
        ret.append(",");
        ret.append("hasNextPage:");
        ret.append(this.hasNextPage);
        ret.append(",");
        ret.append("autoCount:");
        ret.append(this.autoCount);
        ret.append(",");
        ret.append(super.toString());
        return ret.toString();
    }
}
