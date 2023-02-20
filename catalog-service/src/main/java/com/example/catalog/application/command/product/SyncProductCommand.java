package com.example.catalog.application.command.product;

import com.example.common.command.Command;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class SyncProductCommand implements Command<Void> {

    @NotEmpty
    private String code;

    @NotEmpty
    private String name;

    @NotEmpty
    private String catalog;

}
