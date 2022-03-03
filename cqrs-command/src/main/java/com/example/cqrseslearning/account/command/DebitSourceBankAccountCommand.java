package com.example.cqrseslearning.account.command;

import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
public class DebitSourceBankAccountCommand {

  @TargetAggregateIdentifier
  private String bankAccountId;
  private String bankTransferId;
  private long amount;

  public DebitSourceBankAccountCommand(String bankAccountId, String bankTransferId, long amount) {
    this.bankAccountId = bankAccountId;
    this.bankTransferId = bankTransferId;
    this.amount = amount;
  }
}
