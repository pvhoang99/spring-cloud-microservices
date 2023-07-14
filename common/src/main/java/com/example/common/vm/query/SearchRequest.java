//package com.example.common.vm.query;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class SearchRequest implements Serializable {
//
//  private static final long serialVersionUID = 8514625832019794838L;
//
//  private List<FilterRequest> filters;
//
//  private List<SortRequest> sorts;
//
//  private Integer page;
//
//  private Integer size;
//
//  public List<FilterRequest> getFilters() {
//    if (Objects.isNull(this.filters)) {
//      return new ArrayList<>();
//    }
//
//    return this.filters;
//  }
//
//  public List<SortRequest> getSorts() {
//    if (Objects.isNull(this.sorts)) {
//      return new ArrayList<>();
//    }
//
//    return this.sorts;
//  }
//
//}
