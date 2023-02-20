package com.example.auth.command.authentication;

import com.example.common.command.Command;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

@Getter
public class LoginCommand implements Command<OAuth2AccessToken> {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
