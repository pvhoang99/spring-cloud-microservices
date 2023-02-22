package com.example.inventory.application.command.product;

import com.example.common.command.Command;
import lombok.Getter;

@Getter
public class SyncProductCommand implements Command<Void> {

    private String code;

    private String name;

}
