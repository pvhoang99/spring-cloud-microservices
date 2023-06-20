package com.example.inventory.application.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class InventoryItemDTO {

  @NotNull
  private InventoryProductDTO inventoryProduct;

  @Positive
  private long quantity;

  private String note;

}
