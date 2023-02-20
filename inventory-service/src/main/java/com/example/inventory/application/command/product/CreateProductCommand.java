package com.example.inventory.application.command.product;

import com.example.common.command.Command;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class CreateProductCommand implements Command<String> {

    @NotNull
    private String name;

    @NotNull
    private String categoryId;

}
