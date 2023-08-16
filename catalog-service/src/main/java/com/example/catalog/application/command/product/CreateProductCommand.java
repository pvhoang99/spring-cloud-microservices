package com.example.catalog.application.command.product;

import com.example.common.command.Command;
import com.example.common.vm.CommandResult;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateProductCommand implements Command<CommandResult<Long>> {

  @NotNull
  private String name;

  private Long price;

  private String description;

  private String image;

  @NotNull
  private Long categoryId;

}
