package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Page<P> implements Serializable {

    private static final long serialVersionUID = -421807182688252598L;

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_SIZE = 10;

    private int currentPage;// 当前页面
    private int pageSize;   // 页面大小
    private int pageCount;  // 总页数
    private long total;     // 结果总数
    private List<P> data;   // 查询结果

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage <= 0 ? DEFAULT_PAGE : currentPage;
    }

    public int searchPage() {
        return this.currentPage - 1;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? DEFAULT_SIZE : pageSize;
    }

    public int searchSize() {
        return this.pageSize;
    }

    public static <P> Page<P> getInstance(int page, int size) {
        Page<P> instance = new Page<>();
        instance.setCurrentPage(page);
        instance.setPageSize(size);
        return instance;
    }

    public Page<P> saveData(org.springframework.data.domain.Page<P> page) {
        this.pageCount = page.getTotalPages();
        this.total = page.getTotalElements();
        this.data = page.getContent();
        return this;
    }

}
