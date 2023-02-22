package com.example.catalog.application.command.product;

import com.example.common.command.Command;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateProductCommand implements Command<String> {

    @NotNull
    private String name;

    private String description;

    private String image;

    private Long money;

    @NotNull
    private String categoryId;

}
