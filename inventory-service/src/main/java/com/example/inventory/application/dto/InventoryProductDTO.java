package com.example.inventory.application.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class InventoryProductDTO {

  @NotNull
  private String code;

  @Positive
  private long money;

}
