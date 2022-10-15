package com.example.auth.command.authentication;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class LoginCommand {

  @NotEmpty
  private String username;

  @NotEmpty
  private String password;
}
