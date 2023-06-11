package com.zetrim.utils;

import com.zetrim.model.PageableFilter;
import com.zetrim.model.Pagination;
import com.zetrim.model.SuccessResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ControllerUtils {

    public static final int MAX_PAGE_SIZE = 100;

    public static Pageable getPageRequest(Integer pageNumber, Integer pageSize, Sort.Direction direction, String sortProperty) {
        if (pageNumber == null) {
            pageNumber = 1;
        }
        if (pageSize == null) {
            pageSize = MAX_PAGE_SIZE;
        } else {
            pageSize = Math.min(pageSize, MAX_PAGE_SIZE);
        }
        return PageRequest.of(pageNumber - 1, pageSize, getSort(direction, sortProperty));
    }

    private static Sort getSort(Sort.Direction direction, String sortProperty) {
        if (direction != null && sortProperty != null) {
            return Sort.by(direction, sortProperty);
        } else if (direction == null && sortProperty == null) {
            return Sort.unsorted();
        }
        throw new IllegalArgumentException("Неверно указаны параметры сортировки");
    }

    public static Pageable getPageRequest(PageableFilter filter) {
        if (filter == null) {
            filter = new PageableFilter();
        }
        validate(filter);
        return getPageRequest(filter.getPageNumber(), filter.getPageSize(), getDirection(filter), filter.getSort());
    }

    private static void validate(PageableFilter filter) {
        if (filter.getPageNumber() != null && filter.getPageNumber() < 1) {
            throw new IllegalArgumentException("Номер страницы должен быть больше либо равен 1");
        }
        if (filter.getPageSize() != null && filter.getPageSize() < 1) {
            throw new IllegalArgumentException("Размер страницы должен быть больше либо равен 1");
        }
    }

    private static Sort.Direction getDirection(PageableFilter filter) {
        if (StringUtils.isEmpty(filter.getOrder())) {
            return null;
        }
        if (StringUtils.equalsIgnoreCase("asc", filter.getOrder()) || StringUtils.equalsIgnoreCase("desc", filter.getOrder())) {
            return StringUtils.isEmpty(filter.getOrder()) ? null : Sort.Direction.fromString(filter.getOrder());
        }
        throw new IllegalArgumentException("Не допустимое значение направления сортировки. Возможные значения: ASC, DESC");
    }

    public static <T> SuccessResult<List<T>> getSuccessResult(Page<T> page) {
        if (page.getPageable().isPaged()) {
            return new SuccessResult<>(page.getContent(), new Pagination(page));
        } else {
            return new SuccessResult<>(page.getContent());
        }
    }
}
