package com.zetrim.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
public class SuccessResult<T> {
    private T data;
    private Pagination pagination;

    public SuccessResult(T data) {
        this(data, (Pagination)null);
    }
}
