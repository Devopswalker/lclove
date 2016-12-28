package com.lcpa.lclove.vo;

/**
 * Created by shaoheng.huang on 2016/12/28.
 */
public class Paging {
    private Integer pageNo;
    private Integer startIndex;
    private Integer pageSize;
    private Integer total;

    public Paging(Integer pageNo, Integer pageSize){
        this.pageNo = pageNo;
        this.startIndex = pageNo*pageSize-pageSize;
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
