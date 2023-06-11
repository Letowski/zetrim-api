package com.zetrim.model;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class Pagination {
    private int pageNumber;
    private int pageSize;
    private int pageTotal;
    private long count;

    public Pagination(Page<?> page) {
        this.pageNumber = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.pageTotal = page.getTotalPages();
        this.count = page.getTotalElements();
    }
}
