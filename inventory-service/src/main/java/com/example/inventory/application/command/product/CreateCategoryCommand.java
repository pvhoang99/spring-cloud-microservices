package com.example.inventory.application.command.product;

import com.example.common.command.Command;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCategoryCommand implements Command<Long> {

    @NotBlank
    private String name;

}
