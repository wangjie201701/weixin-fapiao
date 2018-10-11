/**
 * 
 */
package com.wangjie.bill.vo.base;

/**
 * 分页请求基类
 */
public class PageRequest {
    private Integer pageSize;
    private Integer pageNo;
    private Integer beginIndex;
   

    public Integer getPageSize() {
        pageSize = (pageSize==null || pageSize<1) ? 5 : pageSize;
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        pageNo = (pageNo==null || pageNo<1) ? 1 : pageNo;
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getBeginIndex() {
        beginIndex = (getPageNo()-1) * getPageSize();
        return beginIndex;
    }

    public void setBeginIndex(Integer beginIndex) {
        this.beginIndex = beginIndex;
    }
}
