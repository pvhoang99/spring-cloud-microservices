package com.example.common.vm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ListQuery<F> {

    @JsonProperty("pager")
    protected Pager pager;

    @JsonProperty("filters")
    private F filters;

    @JsonProperty("sort")
    protected Sort sort;

    public static <F> ListQuery<F> of(Pager pager, F filters, Sort sort) {
        ListQuery<F> query = new ListQuery<>();
        query.pager = pager;
        query.filters = filters;
        query.sort = sort;

        return query;
    }

    public Pager getPager() {
        return this.pager != null ? this.pager : Pager.fallback();
    }

    public Sort getSort() {
        return this.sort != null ? this.sort : Sort.fallback();
    }

}
