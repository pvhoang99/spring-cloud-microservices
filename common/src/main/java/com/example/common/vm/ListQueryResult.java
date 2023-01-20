package com.example.common.vm;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ListQueryResult<F, I> {

    @JsonProperty("pager")
    private Pager pager;

    @JsonProperty("sort")
    private Sort sort;

    @JsonProperty("filters")
    private F filters;

    @JsonProperty("data")
    private List<I> data;

    @JsonProperty("total")
    private Long total;

    public static <F, I> ListQueryResult<F, I> of(ListQuery<F> query, List<I> data, Long total) {
        ListQueryResult<F, I> result = new ListQueryResult<>();
        result.pager = query.getPager();
        result.sort = query.getSort();
        result.filters = query.getFilters();
        result.data = data;
        result.total = total;

        return result;
    }

}
