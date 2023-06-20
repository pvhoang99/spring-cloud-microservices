package com.example.common.vm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListQuery<F> {

  @JsonProperty(value = "filters")
  private F filters;

  @JsonProperty(value = "pager")
  private Pageable pager;

  public static <F> ListQuery<F> of(F filters) {
    ListQuery<F> query = new ListQuery<>();
    query.filters = filters;

    return query;
  }

  public Pageable getPager() {
    return this.pager != null ? this.pager : PageRequest.of(0, 20);
  }

}
