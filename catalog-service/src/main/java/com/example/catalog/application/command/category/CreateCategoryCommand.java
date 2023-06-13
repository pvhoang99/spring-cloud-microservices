package com.example.catalog.application.command.category;

import com.example.common.command.Command;
import javax.validation.constraints.NotBlank;

import com.example.common.vm.CommandResult;
import lombok.Getter;

@Getter
public class CreateCategoryCommand implements Command<CommandResult<Long>> {

    @NotBlank
    private String name;

}
