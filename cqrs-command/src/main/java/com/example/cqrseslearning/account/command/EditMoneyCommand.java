package com.example.cqrseslearning.account.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditMoneyCommand {

  @TargetAggregateIdentifier
  private String bankAccountId;

  private long money;

}
