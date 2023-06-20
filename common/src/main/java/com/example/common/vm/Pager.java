package com.example.common.vm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Pager {

  private static final int FALLBACK_PAGE = 1;
  private static final int FALLBACK_MIN_SIZE = 1;
  private static final int FALLBACK_SIZE = 20;
  private static final int FALLBACK_MAX_SIZE = 10000;

  @JsonProperty("page")
  Integer page;

  @JsonProperty("size")
  Integer size;

  public static Pager fallback() {
    Pager pager = new Pager();
    pager.page = FALLBACK_PAGE;
    pager.size = FALLBACK_SIZE;

    return pager;
  }

  public static Pager of(Integer page, Integer size) {
    Pager pager = new Pager();
    pager.page = page;
    pager.size = size;

    return pager;
  }

  public Integer getPage() {
    return (this.page == null || this.page <= 0) ? FALLBACK_PAGE : this.page;
  }

  public Integer getSize() {
    return (this.size == null || this.size > FALLBACK_MAX_SIZE || this.size < FALLBACK_MIN_SIZE)
        ? FALLBACK_SIZE : this.size;
  }

}
