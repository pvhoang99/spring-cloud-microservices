package com.example.auth.command.user;

import com.example.common.command.Command;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateUserCommand implements Command<Long> {

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
