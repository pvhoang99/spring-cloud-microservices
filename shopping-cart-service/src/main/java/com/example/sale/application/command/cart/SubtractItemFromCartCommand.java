package com.example.sale.application.command.cart;

import com.example.common.command.Command;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SubtractItemFromCartCommand implements Command<Void> {

  @NotNull
  @JsonIgnore
  @Setter
  private Long cartId;

  private Long productId;

  private Long quantity;

}
