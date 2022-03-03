package com.example.cqrseslearning.account.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceBankAccountDebitRejectedEvent {

  private String bankTransferId;

  public SourceBankAccountDebitRejectedEvent(String bankTransferId) {
    this.bankTransferId = bankTransferId;
  }
}
