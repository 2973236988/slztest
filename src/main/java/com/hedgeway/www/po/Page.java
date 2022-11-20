package com.hedgeway.www.po;

import java.util.List;

/**
 * @Description:
 * @Class: Page
 * @Package: cn.ithedge.csdn.domain
 * @Author: hedgeway
 * @CreateTime: 2022/10/20 11:02
 */
public class Page<T> {
    // 总数据量
    private int totalCount;
    // 总页码
    private int totalPage;
    // 每页的数据
    private List<T> list;
    //当前页码
    private int currentPage;
    //每页显示的记录数
    private int rows = 10;


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}

