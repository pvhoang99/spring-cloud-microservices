package com.example.catalog.application.query.product;

import com.example.catalog.application.vm.ProductVm;
import com.example.common.query.Query;
import jakarta.validation.constraints.NotNull;
import java.util.Set;
import lombok.Getter;

@Getter
public class GetProductsByIdsQuery implements Query<Set<ProductVm>> {

  @NotNull
  private Set<Long> ids;

}
