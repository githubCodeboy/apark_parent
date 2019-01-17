package com.apark.common.utils;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T> implements Serializable {

    private int pageNum;
    private int pageSize;
    private int size;
    private int startRow;
    private int endRow;
    private long total;
    private int pages;
    private List<T> list = new ArrayList();
    private int firstPage;
    private int prePage;
    private int nextPage;
    private int lastPage;
    private boolean _isFirstPage = false;
    private boolean _isLastPage = false;
    private boolean hasPreviousPage = false;
    private boolean hasNextPage = false;


    public Page() {
    }

    public int getPageNum() {
        if(this.pageNum ==0){
            return  1;
        }

        else{
            return this.pageNum;
        }

    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        if(this.size == 0)
            return 10;
        else
            return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {

        if(this.getPageNum() ==0){
            return  0;
        }else{
            return  (this.getPageNum()-1)*this.getSize();
        }
    }

    public void setStartRow(int startRow) {

        this.startRow = startRow;
    }

    public int getEndRow() {
        if(this.getPageNum() ==0){
            return this.getSize() ;
        }else{
            return  this.getPageNum()*this.getSize();
        }

    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getFirstPage() {
        return this.firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPrePage() {
        return this.prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return this.nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getLastPage() {
        return this.lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public void set_isFirstPage(boolean _isFirstPage) {
        this._isFirstPage = _isFirstPage;
    }

    public void set_isLastPage(boolean _isLastPage) {
        this._isLastPage = _isLastPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public boolean is_isFirstPage() {
        return _isFirstPage;
    }

    public boolean is_isLastPage() {
        return _isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }
}
