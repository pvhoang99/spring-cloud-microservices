package com.example.auth.command.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class UpdateUserCommand {

    @Setter
    @JsonIgnore
    @TargetAggregateIdentifier
    private String id;

    @NotNull
    private String password;

    private String fullName;

    private String email;

    private String image;
}
