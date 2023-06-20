package com.example.common.vm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Sort {

  private static final String FALLBACK_FIELD = "createdAt";
  private static final String FALLBACK_DIR = "DESC";

  @JsonProperty("field")
  private String field;

  @JsonProperty("dir")
  private String dir;

  public static Sort fallback() {
    Sort sort = new Sort();
    sort.field = FALLBACK_FIELD;
    sort.dir = FALLBACK_DIR;

    return sort;
  }

  public String getField() {
    return this.field == null ? FALLBACK_FIELD : this.field;
  }

  public String getDir() {
    return this.dir == null ? FALLBACK_DIR : this.dir;
  }

}
