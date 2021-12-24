package com.example.cqrseslearning.account.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
public class CreateAccountCommand {

  @TargetAggregateIdentifier
  private String bankAccountId;

  private long overdraftLimit;
}





