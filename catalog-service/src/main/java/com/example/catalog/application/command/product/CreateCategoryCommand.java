package com.example.catalog.application.command.product;

import com.example.common.command.Command;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCategoryCommand implements Command<String> {

    @NotBlank
    private String name;

}
