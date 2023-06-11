package com.zetrim.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class PageableFilter {
    private Integer pageNumber = 1;
    private Integer pageSize = 100;
    private String order = defaultOrder();
    private String sort = defaultSort();

    protected String defaultOrder() {
        return "desc";
    }
    protected String defaultSort() {
        return "createdAt";
    }
}
