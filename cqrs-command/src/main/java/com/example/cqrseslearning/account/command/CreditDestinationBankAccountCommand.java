package com.example.cqrseslearning.account.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreditDestinationBankAccountCommand {

  @TargetAggregateIdentifier
  private String bankAccountId;
  private String bankTransferId;
  private long amount;
}
