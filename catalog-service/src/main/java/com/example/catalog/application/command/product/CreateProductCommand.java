package com.example.catalog.application.command.product;

import com.example.common.command.Command;

import javax.validation.constraints.NotNull;

import com.example.common.vm.CommandResult;
import lombok.Getter;

@Getter
public class CreateProductCommand implements Command<CommandResult<Long>> {

    @NotNull
    private String name;

    private String description;

    private String image;

    @NotNull
    private Long categoryId;

}
