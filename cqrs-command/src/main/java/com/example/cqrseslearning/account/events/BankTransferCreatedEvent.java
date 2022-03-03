package com.example.cqrseslearning.account.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BankTransferCreatedEvent {

  private String bankTransferId;
  private String sourceBankAccountId;
  private String destinationBankAccountId;
  private long amount;

}
