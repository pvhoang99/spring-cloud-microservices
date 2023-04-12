package com.example.common.vm;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Data
public class ListQueryResult<I> {

    @JsonProperty(value = "data")
    private List<I> data;

    @JsonProperty(value = "total")
    private Long total;

    @JsonProperty(value = "pager")
    private Pageable pager;

    public static <I> ListQueryResult<I> of(Page<I> page, Pageable pager) {
        ListQueryResult<I> result = new ListQueryResult<>();
        result.data = page.getContent();
        result.total = page.getTotalElements();
        result.pager = pager;

        return result;
    }

}
