package com.example.auth.command.user;

import com.example.common.command.Command;
import com.example.common.vm.CommandResult;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdateUserCommand implements Command<CommandResult<Long>> {

    @Setter
    private Long id;

}
