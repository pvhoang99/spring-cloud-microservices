package com.example.cqrseslearning.account.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class MarkBankTransferCompletedCommand {

  @TargetAggregateIdentifier
  private String bankTransferId;
}
