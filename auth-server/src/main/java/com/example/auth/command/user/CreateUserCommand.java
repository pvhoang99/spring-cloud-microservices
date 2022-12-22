package com.example.auth.command.user;

import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateUserCommand {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String fullName;

    @NotNull
    private String email;

    private String image;
}
