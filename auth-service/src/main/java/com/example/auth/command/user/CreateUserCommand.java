package com.example.auth.command.user;

import com.example.common.command.Command;
import com.example.common.vm.CommandResult;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateUserCommand implements Command<CommandResult<Long>> {

  @NotBlank
  private String username;

  @NotBlank
  private String password;

  @NotBlank
  private String fullName;

  @NotBlank
  private String email;

  private String image;

}
