package com.example.inventory.application.query.product;

import com.example.common.query.Query;
import com.example.inventory.application.vm.ProductVm;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class GetProductQuery implements Query<ProductVm> {

  @NotBlank
  private String code;

}
